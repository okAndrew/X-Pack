package com.epam.lab.controller.services.token4upload;

import java.sql.Timestamp;
import java.util.Random;

import com.epam.lab.controller.dao.token4upload.Token4UploadDAO;
import com.epam.lab.controller.dao.token4upload.Token4UploadDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Token4Upload;

public class Token4UploadServiceImpl extends AbstractServiceImpl<Token4Upload>
		implements Token4UploadService {

	private Token4UploadDAO tokenDAO = ((Token4UploadDAO) dao);

	public Token4UploadServiceImpl() {
		super(new Token4UploadDAOImpl());
	}

	public Token4Upload createToken(long idUser, long liveTime, int maxNumUse) {
		Token4Upload token4Upload = new Token4Upload();
		token4Upload.setIdUser(idUser);
		token4Upload.setMaxNumUse(maxNumUse);
		token4Upload.setDestroyDate(getDestroyDate(liveTime));
		token4Upload.setToken(generateToken());
		while (insert(token4Upload) == 0) { // if token has already in DB
			token4Upload.setToken(generateToken());
		}
		return tokenDAO.getByToken(token4Upload.getToken());
	}

	public Token4Upload getByToken(String token) {
		return tokenDAO.getByToken(token);
	}

	public int deleteNotActiveTokens() {
		return tokenDAO.deleteNotActiveTokens();
	}

	private Timestamp getDestroyDate(long liveTime) {
		long timeInMiliSeconds = liveTime * 1000;
		long time = CurrentTimeStamp.getCurrentTimeStamp().getTime()
				+ timeInMiliSeconds;
		Timestamp result = new Timestamp(time);
		return result;
	}

	private String generateToken() {
		String token = null;
		Timestamp currentTimeStamp = CurrentTimeStamp.getCurrentTimeStamp();
		token = new MD5Encrypter().encrypt(currentTimeStamp.toString()
				+ new Random().nextLong());
		return token;
	}
}
