package com.epam.lab.controller.dao.token4auth;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Token4Auth;

public interface Token4AuthDAO extends GenericDAO<Token4Auth> {
	public Token4Auth getByToken(String token);

	public int deleteNotActiveTokens();

}
