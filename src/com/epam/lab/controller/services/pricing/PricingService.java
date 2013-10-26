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
		Timestamp curTime = TimeStampManager.getCurrentTime();
		Timestamp timeEnd = TimeStampManager.getCurrentTime();
		TimeStampManager.addNumberOfMonth(timeEnd, months);
		
		user = userService.get(userId);
		Tariff curTariff = tariffServise.get(user.getIdTariff());
		Tariff newTariff = tariffServise.get(tariffId);
		Payment curPayment = paymentService.getCurrentPayment(user.getId());
		
		if (curTariff != null && curTariff.getId() == newTariff.getId()) {
			continueCurrentTariff(months);
		}
	}
	
	private void continueCurrentTariff(int months) {
		Tariff tariff = new TariffServiseImpl().get(user.getIdTariff());
		List<Payment> payments = new PaymentServiceImpl().getAvailableUserPays(user.getId());
		Timestamp timeStart = payments.get(payments.size() - 1).getDateEnd();
		Timestamp timeEnd = TimeStampManager.addNumberOfMonth((Timestamp)timeStart.clone(), months);
		double price = tariff.getPrice() * months;
		
		Payment payment = new Payment();
		payment.setUser(user.getId());
		payment.setTariff(tariff.getId());
		payment.setDateCreated(timeStart);
		payment.setDateEnd(timeEnd);
		payment.setPrice(price);
		payment.setAvailable(true);
		payment.setStatus(true);
		
		new PaymentServiceImpl().insert(payment);
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
	
}
