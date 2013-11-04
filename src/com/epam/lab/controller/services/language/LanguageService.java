package com.epam.lab.controller.services.language;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Language;

public interface LanguageService extends AbstractService<Language> {

	Language getLang(String locale);

}
