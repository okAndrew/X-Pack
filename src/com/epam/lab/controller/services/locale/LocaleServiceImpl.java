package com.epam.lab.controller.services.locale;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.locale.LocaleDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.Locale;

public class LocaleServiceImpl extends AbstractServiceImpl<Locale> implements
		LocaleService {
	static Logger logger = Logger.getLogger(LocaleServiceImpl.class);

	public LocaleServiceImpl() {
		super(new LocaleDAOImpl());
	}

	@Override
	public Locale getByLocale(String locale) {
		LocaleDAOImpl daoImpl = new LocaleDAOImpl();
		return daoImpl.getByLocale(locale);
	}

}