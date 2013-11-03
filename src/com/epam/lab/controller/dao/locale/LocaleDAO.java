package com.epam.lab.controller.dao.locale;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Locale;

public interface LocaleDAO extends GenericDAO<Locale> {
	Locale getByLocale(String locale);

}