package com.epam.lab.controller.services.tariff;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Tariff;

public interface TariffServise extends AbstractService<Tariff> {

	int updateTariff(String id, String name, String maxCapacity, String price,
			String position, String description);

	void deteteTariffs(String[] checkTariffs);

	void activateTariffs(String[] checkTariffs);

	List<Tariff> getAvailableTariffs();

	String checkParametersTariff(String name, String maxCapacity, String price,
			String position, String description);

	Tariff get(long id, String language);

	List<Tariff> getAll(String language);

	List<Tariff> getAvailableTariffs(String language);

	long getCount();

	List<Tariff> getByParam(String page, String count, String orderBy,
			String sop, String language);

}
