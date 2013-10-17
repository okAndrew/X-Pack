package com.epam.lab.controller.services;

import java.util.List;

import com.epam.lab.controller.dao.TariffDAO;
import com.epam.lab.controller.dao.impl.TariffDAOImpl;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;

public class TariffServise {

	private TariffDAO tariffDao = new TariffDAOImpl();

	public List<Tariff> getAllTariffs() {
		return tariffDao.getAll();
	}

	public void addTariff(String name, Integer maxCapacity) {
		Tariff tariff = new Tariff();
		tariff.setName(name);
		tariff.setMaxCapacity(maxCapacity);
		tariffDao.insert(tariff);
	}

	public List<Tariff> getAvailableTariffs() {
		return tariffDao.getAvailableTariffs();
	}

	public Tariff getTariff(long id) {
		return new TariffDAOImpl().get(id);
	}

	public int changeTariff(long userId, long tariffId) {
		int result = 0;
		User user = new UserService().getUserById(userId);
		TariffServise tariffServise = new TariffServise();
		Tariff tariff = tariffServise.getTariff(tariffId);
		Tariff userTariff = tariffServise.getTariff(user.getIdTariff());

		if (userTariff.getPosition() < tariff.getPosition()) {
			new PaymentService().createPayment(user, tariff);
			result = 1;
		}
		
		return result;
	}

}