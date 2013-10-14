package com.epam.lab.controller.services;

import java.util.List;

import com.epam.lab.controller.dao.TariffDAO;
import com.epam.lab.controller.dao.impl.TariffDAOImpl;
import com.epam.lab.model.Tariff;

public class TariffServise {

	private TariffDAO tariffDao = new TariffDAOImpl();

	public List<Tariff> getAllTariffs() {
		return tariffDao.getAll();
	}

	public void addTariff(String name, Integer maxCapacity){
		Tariff tariff = new Tariff();
		tariff.setName(name);
		tariff.setMaxCapacity(maxCapacity);
		tariffDao.insert(tariff);
	}
}
