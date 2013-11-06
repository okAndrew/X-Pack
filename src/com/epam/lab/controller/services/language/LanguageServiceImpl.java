package com.epam.lab.controller.services.language;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.language.LanguageDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.locale.LocaleServiceImpl;
import com.epam.lab.model.Language;
import com.epam.lab.model.Locale;

public class LanguageServiceImpl extends AbstractServiceImpl<Language>
		implements LanguageService {
	static Logger logger = Logger.getLogger(LanguageServiceImpl.class);

	public LanguageServiceImpl() {
		super(new LanguageDAOImpl());
	}

	@Override
	public Language getLang(String locale) {
		LocaleServiceImpl locImpl = new LocaleServiceImpl();
		Locale language = locImpl.getByLocale(locale);
		LanguageDAOImpl daoimpl = new LanguageDAOImpl();
		Language languageObj = daoimpl.getByLang(language.getLanguage());
		return languageObj;

	}

}