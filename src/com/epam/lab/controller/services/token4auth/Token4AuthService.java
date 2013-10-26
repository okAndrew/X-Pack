package com.epam.lab.controller.services.token4auth;

import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.exceptions.notfound.UserNotFoundException;
import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Token4Auth;

public interface Token4AuthService extends AbstractService<Token4Auth> {
	public Token4Auth createToken(long idUser, long liveTime);

	public Token4Auth getByToken(String token);

	public int deleteNotActiveTokens();

	public boolean verifyAccessToFolder(String token, long idFolder)
			throws TokenNotFoundException, UserNotFoundException,
			FolderNotFoundException;
}
