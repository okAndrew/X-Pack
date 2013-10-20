package com.epam.lab.controller.services.tariff;

import com.epam.lab.controller.dao.tariff.TariffDAO;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.payment.PaymentServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;

public class TariffServiseImpl extends AbstractServiceImpl<Tariff> implements
		TariffServise {
	private TariffDAO tariffDao = new TariffDAOImpl();

	public TariffServiseImpl() {
		super(new TariffDAOImpl());
	}

	// rename to insert(Tariff tariff)
	public void addTariff(String name, String maxCapacity, String price,
			String position, String description) {
		Tariff tariff = new Tariff();
		tariff.setName(name).setMaxCapacity(Integer.parseInt(maxCapacity))
				.setPrice(Double.parseDouble(price))
				.setPosition(Integer.parseInt(position))
				.setDescription(description);
		tariffDao.insert(tariff);
	}

	public int changeTariff(long userId, long tariffId) {
		int result = 0;
		User user = new UserServiceImpl().get(userId);
		TariffServiseImpl tariffServise = new TariffServiseImpl();
		Tariff tariff = tariffServise.get(tariffId);
		Tariff userTariff = tariffServise.get(user.getIdTariff());

		if (userTariff.getPosition() < tariff.getPosition()) {
			new PaymentServiceImpl().createPayment(user, tariff);
			result = 1;
		}

		return result;
	}

	@Override
	public int updateTariff(String id, String name, String maxCapacity,
			String price, String position, String description) {
		Tariff tariff = new Tariff();
		tariff.setId(Long.parseLong(id)).setName(name)
				.setMaxCapacity(Integer.parseInt(maxCapacity))
				.setPrice(Double.parseDouble(price))
				.setPosition(Integer.parseInt(position))
				.setDescription(description);
		return tariffDao.update(tariff);
	}

	@Override
	public String deteteTariffs(String[] checkTariffs) {
		String errorMesage = null;
		if (checkTariffs == null){
			errorMesage = "Please check the tariffs you want to delete!!!";
		} else {
			for(int i=0; i<checkTariffs.length; i++){
				tariffDao.delete(Long.parseLong(checkTariffs[i]));
			}
		}
		return errorMesage;
	}

	@Override
	public String activateTariffs(String[] checkTariffs) {
		String errorMesage = null;
		if (checkTariffs == null){
			errorMesage = "Please check the tariffs you want to activate!!!";
		} else {
			for(int i=0; i<checkTariffs.length; i++){
				tariffDao.isActivate(Long.parseLong(checkTariffs[i]));
			}
		}
		return errorMesage;
	}

}