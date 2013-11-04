package com.epam.lab.controller.services.locale;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Locale;

public interface LocaleService extends AbstractService<Locale> {
	Locale getByLocale(String locale);

}
