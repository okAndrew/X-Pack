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
	HttpServletRequest request = null;
	User user = null;
	
	public PricingService(HttpServletRequest request) {
		this.request = request;
		tariffServise = new TariffServiseImpl();
		userService = new UserServiceImpl();
		session = this.request.getSession(false);
		timeManager = new TimeStampManager();
		paymentService = new PaymentServiceImpl();
	}

	public HttpServletRequest initialize() {
		if (session != null) {
			user = userService.get(Long.valueOf(session.getAttribute("userid").toString()));
			Payment curPayment = paymentService.getCurrentPayment(user.getId());
			
			if (curPayment != null) {
				int daysBetween = TimeStampManager.daysBetween(curPayment.getDateCreated(), curPayment.getDateEnd());
				int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), curPayment.getDateEnd());
				double savedCash = curPayment.getPrice() / daysBetween * daysLeft;
				request.setAttribute("savedCash", Math.round(savedCash * 100.0) / 100.0);
				request.setAttribute("daysLeft", daysLeft);
				request.setAttribute("daysBetween", daysBetween);
			}
			
			request.setAttribute("currentTariff", tariffServise.get(user.getIdTariff()));
		}
		
		request.setAttribute("tariffs", new TariffServiseImpl().getAvailableTariffs());
		
		return request;
	}
	
}
