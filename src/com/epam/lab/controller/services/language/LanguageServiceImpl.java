package com.epam.lab.controller.services.language;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.language.LanguageDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.Language;

public class LanguageServiceImpl extends AbstractServiceImpl<Language>
		implements LanguageService {
	static Logger logger = Logger.getLogger(LanguageServiceImpl.class);

	public LanguageServiceImpl() {
		super(new LanguageDAOImpl());
	}

	@Override
	public Language getByLocale(String locale) {
		LanguageDAOImpl daoImpl = new LanguageDAOImpl();
		return daoImpl.getByLocale(locale);
	}

}