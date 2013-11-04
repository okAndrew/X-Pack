package com.epam.lab.controller.dao.token;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Token;

public interface TokenDAO extends GenericDAO<Token> {

	Token get(String token);
}
