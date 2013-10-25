package com.epam.lab.controller.services.pricing;

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
	TimeStampManager timeManager = null;
	PaymentServiceImpl paymentService = null;
	HttpSession session = null;
	User user = null;
	
	public HttpServletRequest initialize(HttpServletRequest request) {
		tariffServise = new TariffServiseImpl();
		userService = new UserServiceImpl();
		session = request.getSession(false);
		timeManager = new TimeStampManager();
		paymentService = new PaymentServiceImpl();
		
		if (session != null) {
			user = userService.get(Long.valueOf(session.getAttribute("userid").toString()));
			Payment curPayment = paymentService.getCurrentPayment(user.getId());
			if (curPayment != null) {
				int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), curPayment.getDateEnd());
				double savedCash = savedCash(curPayment);
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
		Payment curPayment = paymentService.getCurrentPayment(user.getId());
		
		if (curTariff != null && curTariff.getId() == newTariff.getId()) {
			String sqlUpdateUser = "UPDATE users SET id_tariff = ? WHERE id = ?";
			String sqlUpdatePayment = "UPDATE payment ";
			String sqlInsertPayment = "INSERT INTO payment() VALUES()";
			
			Object[] updateUserArgs = {newTariff.getId(), user.getId()};
			Object[] updatePayment = {newTariff.getId(), user.getId()};
			Object[] insertPayment = {newTariff.getId(), user.getId()};
			
			Object[][] args = {updateUserArgs, updatePayment, insertPayment};
			String[] sqls = {sqlUpdateUser, sqlUpdatePayment, sqlInsertPayment};
			
			new PaymentDAOImpl().pay(sqls, args);
		}
	}
	
	private double savedCash(Payment currentPayment) {
		int daysBetween = TimeStampManager.daysBetween(currentPayment.getDateCreated(), currentPayment.getDateEnd());
		int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), currentPayment.getDateEnd());
		double savedCash = currentPayment.getPrice() / daysBetween * daysLeft;
		
		return savedCash;
	}
	
}
