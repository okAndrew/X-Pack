package com.epam.lab.controller.dao.tariff;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Tariff;

public interface TariffDAO extends GenericDAO<Tariff> {

	List<Tariff> getAvailableTariffs();

	int setIsDelete(boolean state, long id);

	List<Tariff> getAll(String language);

	Tariff get(long id, String language);

	List<Tariff> getAvailableTariffs(String language);

	long getCount();

	List<Tariff> getByParam(Integer page, Integer count, String orderBy,
			String sop, String language);

}
