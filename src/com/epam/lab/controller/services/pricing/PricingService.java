package com.epam.lab.controller.services.pricing;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;

public class PricingService {
	
	TariffServiseImpl tariffServise = null;
	UserServiceImpl userService = null;
	PaymentServiceImpl paymentService = null;
	HttpSession session = null;
	User user = null;
	
	public HttpServletRequest initialize(HttpServletRequest request) {
		tariffServise = new TariffServiseImpl();
		userService = new UserServiceImpl();
		session = request.getSession(false);
		paymentService = new PaymentServiceImpl();
		
		if (session != null) {
			user = userService.get(Long.valueOf(session.getAttribute("userid").toString()));
			Payment curPayment = paymentService.getCurrentPayment(user.getId());
			if (curPayment != null) {
				int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), curPayment.getDateEnd());
				double savedCash = savedCash();
				request.setAttribute("savedCash", Math.round(savedCash * 100.0) / 100.0);
				request.setAttribute("daysLeft", daysLeft);
			}
			request.setAttribute("currentTariff", tariffServise.get(user.getIdTariff()));
		}
		request.setAttribute("tariffs", new TariffServiseImpl().getAvailableTariffs());
		
		return request;
	}
	
	public void pay(long userId, long tariffId, int months) {
		tariffServise = new TariffServiseImpl();
		userService = new UserServiceImpl();
		paymentService = new PaymentServiceImpl();
		
		user = userService.get(userId);
		Tariff curTariff = tariffServise.get(user.getIdTariff());
		Tariff newTariff = tariffServise.get(tariffId);
		
		if (paymentService.getAllPayByUserId(user.getId()) != null) {
			if (curTariff != null && curTariff.getId() == newTariff.getId()) {
				continueCurrentTariff(months, curTariff);
			}
			if (curTariff.getPrice() < newTariff.getPrice()) {
				newHigherTariff(months, curTariff, newTariff);
			}
		} else {
			createFirstuserPayment(newTariff, months);
		}
	}
	
	public void deactivateOverdueTariff() {
		
	}
	
	private void continueCurrentTariff(int months, Tariff tariff) {
		List<Payment> payments = new PaymentServiceImpl().getAvailableUserPays(user.getId());
		Timestamp timeStart = payments.get(payments.size() - 1).getDateEnd();
		Timestamp timeEnd = TimeStampManager.addNumberOfMonth((Timestamp)timeStart.clone(), months);
		double price = tariff.getPrice() * months;
		
		Payment payment = createPayment(user.getId(), tariff.getId(), timeStart, timeEnd, price, true, true);
		
		new PaymentServiceImpl().insert(payment);
	}
	
	private void newHigherTariff(int months, Tariff curTariff, Tariff newTariff) {
		String sqlUpdateUser = "UPDATE users SET id_tariff = ? WHERE id = ?";
		String sqlUpdatePayment = "UPDATE payments SET date_end = ?, available = 0 WHERE available = 1 AND user = ?";
		String sqlCreatePayment = "INSERT INTO payments(user, tariff, date_created, date_end, price, payments.status, payments.available) VALUES(?, ?, ?, ?, ?, ?, ?);";
		paymentService = new PaymentServiceImpl();
		
		months--;
		double price = 0;
		do {
			months++;
			price += newTariff.getPrice() * months - savedCash();
		} while (price <= 0);
		
		Timestamp timeStart = TimeStampManager.getCurrentTime();
		Timestamp timeEnd = TimeStampManager.addNumberOfMonth((Timestamp)timeStart.clone(), months);
		
		Object[] argUpdateUser = {newTariff.getId(), user.getId()};
		Object[] argUpdatePayment = {timeStart, user.getId()};
		Object[] argCreatePayment = {user.getId(), newTariff.getId(), timeStart, timeEnd, price, 1, 1};
		
		String[] sql = {sqlUpdateUser, sqlUpdatePayment, sqlCreatePayment};
		Object[][] args = {argUpdateUser, argUpdatePayment, argCreatePayment};
		
		new PaymentDAOImpl().pay(sql, args);
	}
	
	private void createFirstuserPayment(Tariff tariff, int months) {
		String sqlUpdateUser = "UPDATE users SET id_tariff = ? WHERE id = ?";
		String sqlCreatePayment = "INSERT INTO payments(user, tariff, date_created, date_end, price, payments.status, payments.available) VALUES(?, ?, ?, ?, ?, ?, ?);";
		
		Timestamp timeStart = TimeStampManager.getCurrentTime();
		Timestamp timeEnd = TimeStampManager.addNumberOfMonth((Timestamp)timeStart.clone(), months);
		
		double price = tariff.getPrice() * months;
		
		Object[] argUpdateUser = {timeStart, user.getId()};
		Object[] argCreatePayment = {user.getId(), tariff.getId(), timeStart, timeEnd, price, 1, 1};
		
		String[] sql = {sqlUpdateUser, sqlCreatePayment};
		Object[][] args = {argUpdateUser, argCreatePayment};
		
		new PaymentDAOImpl().pay(sql, args);
	}
	
	private double savedCash() {
		List<Payment> payments = new PaymentServiceImpl().getAvailableUserPays(user.getId());
		double savedCash = 0;
		
		for (int i = 0; i < payments.size(); i++) {
			int daysBetween = TimeStampManager.daysBetween(payments.get(i).getDateCreated(), payments.get(i).getDateEnd());
			if (daysBetween != 0) {
				int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), payments.get(i).getDateEnd());
				savedCash += payments.get(i).getPrice() / daysBetween * daysLeft;
			} else {
				savedCash += payments.get(i).getPrice();
			}
		}
		
		return savedCash;
	}
	
	private Payment createPayment(long userId, long tariffId, Timestamp timeStart, Timestamp timeEnd, double price, boolean status, boolean available) {
		Payment payment = new Payment();
		payment.setUser(user.getId());
		payment.setTariff(tariffId);
		payment.setDateCreated(timeStart);
		payment.setDateEnd(timeEnd);
		payment.setPrice(price);
		payment.setStatus(status);
		payment.setAvailable(available);
		
		return payment;
	}
	
}
