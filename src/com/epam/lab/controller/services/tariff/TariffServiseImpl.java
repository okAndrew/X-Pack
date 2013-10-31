package com.epam.lab.controller.services.tariff;

import java.util.List;

import com.epam.lab.controller.dao.tariff.TariffDAO;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Tariff;

public class TariffServiseImpl extends AbstractServiceImpl<Tariff> implements
		TariffServise {

	private TariffDAO tariffDao = new TariffDAOImpl();

	public TariffServiseImpl() {
		super(new TariffDAOImpl());
	}

	public String addTariff(String name, String maxCapacity, String price,
			String position, String description) {
		String errorMessage = checkParametersTariff(name, maxCapacity, price,
				position, description);
		if (errorMessage == null) {
			Tariff tariff = new Tariff();
			tariff.setName(name)
					.setMaxCapacity(Long.parseLong(maxCapacity) * 1024 * 1024)
					.setPrice(Double.parseDouble(price))
					.setPosition(Integer.parseInt(position))
					.setDescription(description);
			tariffDao.insert(tariff);
		}
		return errorMessage;
	}

	@Override
	public String updateTariff(String id, String name, String maxCapacity,
			String price, String position, String description) {
		String errorMessage = null;
		errorMessage = checkParametersTariff(name, maxCapacity, price,
				position, description);
		if (errorMessage == null) {
			Tariff tariff = new Tariff();
			tariff.setId(Long.parseLong(id))
					.setName(name)
					.setMaxCapacity(Integer.parseInt(maxCapacity) * 1024 * 1024)
					.setPrice(Double.parseDouble(price))
					.setPosition(Integer.parseInt(position))
					.setDescription(description);
			tariffDao.update(tariff);
		}
		return errorMessage;
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

	private String checkParametersTariff(String name, String maxCapacity,
			String price, String position, String description) {
		List<Tariff> tariffs = getAll();
		String errorMessage = null;
		errorMessage = validateParam(name, maxCapacity, price, position,
				description);
		if (errorMessage == null) {
			for (Tariff iter : tariffs) {
				if (iter.getName().equalsIgnoreCase(name)) {
					errorMessage = "Tariff with the same name already exists!!!";
					return errorMessage;
				}
			}
		}
		return errorMessage;
	}

	private String validateParam(String name, String maxCapacity, String price,
			String position, String description) {
		String errorMessage = null;
		if (!Validator.TARIFF_NAME.validate(name)) {
			errorMessage = "Name tarrif is not correct!! Please use symbols a-z and A-Z";
			return errorMessage;
		} else if (!Validator.INTEGERS.validate(maxCapacity)) {
			errorMessage = "MaxCapacity tarrif is not correct!! Please use symbols 0-9";
			return errorMessage;
		} else if (!Validator.DECIMALS.validate(price)) {
			errorMessage = "Price tarrif is not correct!! Please use symbols 0-9 and '.' (example XXXX.XX)";
			return errorMessage;
		} else if (!Validator.INTEGERS.validate(position)) {
			errorMessage = "Positions tarrif is not correct!! Please use symbols 0-9";
			return errorMessage;
		} else if (description == null) {
			errorMessage = "Description can not is empty!";
			return errorMessage;
		}
		return errorMessage;
	}

	@Override
	public long getCount() {
		return tariffDao.getCount();
	}

}