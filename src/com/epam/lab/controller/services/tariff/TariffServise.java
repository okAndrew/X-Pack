package com.epam.lab.controller.services.tariff;

import java.util.HashMap;
import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Tariff;

public interface TariffServise extends AbstractService<Tariff> {

	String updateTariff(String id, String name, String maxCapacity,
			String price, String position, String descriptionUS, String descriptionRU, String descriptionUA);

	void deteteTariffs(String[] checkTariffs);

	void activateTariffs(String[] checkTariffs);

	List<Tariff> getAvailableTariffs();

	Tariff get(long id, String language);

	List<Tariff> getAll(String language);

	List<Tariff> getAvailableTariffs(String language);

	long getCount();

	List<Tariff> getByParam(String page, String count, String orderBy,
			String sop, String language);

	HashMap<Long, Tariff> getHashMapNames();

}
