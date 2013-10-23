package com.epam.lab.controller.services.token4upload;

import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.exceptions.notfound.UserNotFoundException;
import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Token4Upload;

public interface Token4UploadService extends AbstractService<Token4Upload> {
	public Token4Upload createToken(long idUser, long liveTime, int maxNumUse);

	public Token4Upload getByToken(String token);

	public int deleteNotActiveTokens();

	public boolean verifyAccessToFolder(String token, long idFolder)
			throws TokenNotFoundException, UserNotFoundException,
			FolderNotFoundException;
}
