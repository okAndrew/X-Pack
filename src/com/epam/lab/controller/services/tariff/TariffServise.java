package com.epam.lab.controller.services.tariff;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Tariff;

public interface TariffServise extends AbstractService<Tariff> {

	int updateTariff(String id, String name, String maxCapacity, String price, String position, String description);
	
	String deteteTariffs(String[] checkTariffs);
	
	String activateTariffs(String[] checkTariffs);
}
