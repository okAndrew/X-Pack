package com.epam.lab.controller.services.pricing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Payment;
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
				double savedCash = savedCash(curPayment);
				request.setAttribute("savedCash", Math.round(savedCash * 100.0) / 100.0);
			}
			request.setAttribute("currentTariff", tariffServise.get(user.getIdTariff()));
		}
		request.setAttribute("tariffs", new TariffServiseImpl().getAvailableTariffs());
		
		return request;
	}
	
	public void pay() {
	}
	
	private double savedCash(Payment currentPayment) {
		int daysBetween = TimeStampManager.daysBetween(currentPayment.getDateCreated(), currentPayment.getDateEnd());
		int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), currentPayment.getDateEnd());
		double savedCash = currentPayment.getPrice() / daysBetween * daysLeft;
		
		return savedCash;
	}
	
}
