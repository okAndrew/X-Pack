package com.epam.lab.controller.services.token4auth;

import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Auth;

public interface Token4AuthService extends AbstractService<Token4Auth> {
	public Token4Auth createToken(long idUser, long liveTime);

	public Token4Auth getByToken(String token);

	public int deleteNotActiveTokens();

	public Folder verifyAccessRequest(String token, long idFolder)
			throws TokenNotFoundException;

	public boolean isActive(Token4Auth tokenData);

}