package com.epam.lab.controller.services.pricing;

import javax.servlet.http.HttpServletRequest;

public interface PricingService {

	HttpServletRequest initialize(HttpServletRequest request);

	void pay(long userId, long tariffId, int months);

	void deactivateOverdueTariff();
}
