package com.epam.lab.controller.dao;

import com.epam.lab.model.Token;

public interface TokenDAO extends GenericDAO<Token> {
	
	Token get(String token);
	
	int activateToken(String token);

	int activateToken(Token token);
	
	int deactivateToken(String token);
	
	int deactivateToken(Token token);
	
}
