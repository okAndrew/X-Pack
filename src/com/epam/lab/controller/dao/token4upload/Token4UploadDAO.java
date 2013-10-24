package com.epam.lab.controller.dao.token4upload;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Token4Upload;

public interface Token4UploadDAO extends GenericDAO<Token4Upload> {
	public Token4Upload getByToken(String token);

	public int deleteNotActiveTokens();

}
