package com.epam.lab.controller.services.token4upload;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Token4Upload;

public interface Token4UploadService extends AbstractService<Token4Upload> {
	public Token4Upload createToken(long idUser, long liveTime, int maxNumUse);

	public int deleteNotActiveTokens();
}
