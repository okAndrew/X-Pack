package com.epam.lab.controller.services.tariff;

import java.util.List;

import com.epam.lab.controller.dao.tariff.TariffDAO;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.Tariff;

public class TariffServiseImpl extends AbstractServiceImpl<Tariff> implements
		TariffServise {
	
	private TariffDAO tariffDao = new TariffDAOImpl();

	public TariffServiseImpl() {
		super(new TariffDAOImpl());
	}

	public String addTariff(String name, String maxCapacity, String price,
			String position, String description) {
		String message = checkParametersTariff(name, maxCapacity, price, position, description);
		if (message == null){
			Tariff tariff = new Tariff();
			tariff.setName(name)
					.setMaxCapacity(Long.parseLong(maxCapacity) * 1024 * 1024)
					.setPrice(Double.parseDouble(price))
					.setPosition(Integer.parseInt(position))
					.setDescription(description);
			tariffDao.insert(tariff);
		}
		return message;
	}

	// public int changeTariff(long userId, long tariffId) {
	// int result = 0;
	// User user = new UserServiceImpl().get(userId);
	// TariffServiseImpl tariffServise = new TariffServiseImpl();
	// Tariff tariff = tariffServise.get(tariffId);
	// Tariff userTariff = tariffServise.get(user.getIdTariff());
	//
	// if (userTariff.getPosition() < tariff.getPosition()) {
	// new PaymentServiceImpl().createPayment(user, tariff);
	// result = 1;
	// }
	//
	// return result;
	// }

	@Override
	public int updateTariff(String id, String name, String maxCapacity,
			String price, String position, String description) {
		Tariff tariff = new Tariff();
		tariff.setId(Long.parseLong(id)).setName(name)
				.setMaxCapacity(Integer.parseInt(maxCapacity) * 1024 * 1024)
				.setPrice(Double.parseDouble(price))
				.setPosition(Integer.parseInt(position))
				.setDescription(description);
		return tariffDao.update(tariff);
	}

	@Override
	public void deteteTariffs(String[] checkTariffs) {
		for (int i = 0; i < checkTariffs.length; i++) {
			tariffDao.delete(Long.parseLong(checkTariffs[i]));
		}
	}

	@Override
	public void activateTariffs(String[] checkTariffs) {
		for (int i = 0; i < checkTariffs.length; i++) {
			tariffDao.setIsDelete(false, Long.parseLong(checkTariffs[i]));
		}
	}

	@Override
	public List<Tariff> getAvailableTariffs() {
		return tariffDao.getAvailableTariffs();
	}

	@Override
	public String checkParametersTariff(String name, String maxCapacity,
			String price, String position, String description) {
		String errorMessage = null;
		List<Tariff> tariffs = getAll();
		if (name == "" || maxCapacity == "" || price == "" || position == ""
				|| description == "") {
			errorMessage = "Fields canoot be empty!!!";
			return errorMessage;
		}
		for (Tariff iter : tariffs) {
			if (iter.getName().equalsIgnoreCase(name)) {
				errorMessage = "Tariff with the same name already exists!!!";
				break;
			}
		}
		return errorMessage;
	}

	@Override
	public long getCount() {
		return tariffDao.getCount();
	}

}