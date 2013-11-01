package com.epam.lab.controller.dao.tariff;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Tariff;

public interface TariffDAO extends GenericDAO<Tariff> {

	List<Tariff> getAvailableTariffs();
	
	int setIsDelete(boolean state, long id);
	
	long getCount();
}
