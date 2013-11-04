package com.epam.lab.controller.services.tariff;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.epam.lab.controller.dao.tariff.TariffDAO;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.SelectService;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Tariff;

public class TariffServiseImpl extends AbstractServiceImpl<Tariff> implements
		TariffServise {

	static Logger logger = Logger.getLogger(TariffServiseImpl.class);

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
					errorMessage = "Tariff_with_the_same_name_already_exists!!!";
					return errorMessage;
				}
			}
		}
		return errorMessage;
	}

	// Locale methods
	@Override
	public List<Tariff> getAll(String language) {
		return tariffDao.getAll(language);
	}

	@Override
	public Tariff get(long id, String language) {
		return tariffDao.get(id, language);
	}

	@Override
	public List<Tariff> getAvailableTariffs(String language) {
		return tariffDao.getAvailableTariffs(language);
	}

	private String validateParam(String name, String maxCapacity, String price,
			String position, String description) {
		String errorMessage = null;
		if (!Validator.TARIFF_NAME.validate(name)) {
			errorMessage = "Name_tarrif_is_not_correct._Please_use_symbols_a-z_and_A-Z";
			return errorMessage;
		} else if (!Validator.INTEGERS.validate(maxCapacity)) {
			errorMessage = "MaxCapacity_tarrif_is_not_correct._Please_use_symbols_0-9";
			return errorMessage;
		} else if (!Validator.DECIMALS.validate(price)) {
			errorMessage = "Price_tarrif_is_not_correct._Please_use_symbols_0-9_and_'.'_(example_XXXX.XX)";
			return errorMessage;
		} else if (!Validator.INTEGERS.validate(position)) {
			errorMessage = "Positions_tarrif_is_not_correct._Please_use_symbols_0-9";
			return errorMessage;
		} else if (description == null) {
			errorMessage = "Description_can_not_be_empty";
			return errorMessage;
		}
		return errorMessage;
	}

	@Override
	public long getCount() {
		return tariffDao.getCount();
	}

	@Override
	public List<Tariff> getByParam(String page, String count, String orderBy,
			String sort, String language) {
		SelectService<Tariff> s = new SelectService<Tariff>();
		TariffDAOImpl daoImpl = new TariffDAOImpl();
		Map<String, String> param = s.getParam(Tariff.class, page, count,
				orderBy, sort);
		int c, p;
		try {
			c = Integer.valueOf(param.get(count));
			p = Integer.valueOf(param.get(page));
		} catch (NumberFormatException e) {
			logger.error(e);
			c = 10;
			p = 0;
		}

		return daoImpl.getByParam(p, c, param.get("order"), param.get("sort"),
				language);
	}

}