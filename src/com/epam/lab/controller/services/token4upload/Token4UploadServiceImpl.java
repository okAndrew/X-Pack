package com.epam.lab.controller.services.token4upload;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import com.epam.lab.controller.dao.token4upload.Token4UploadDAO;
import com.epam.lab.controller.dao.token4upload.Token4UploadDAOImpl;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.exceptions.notfound.UserNotFoundException;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserService;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Upload;
import com.epam.lab.model.User;

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
		do {
			token4Upload.setToken(generateToken());
		} while (insert(token4Upload) == 0);// if token has already in DB
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
		Timestamp time = new Timestamp(new Date().getTime());
		Timestamp result = new Timestamp(new Date().getTime()
				+ timeInMiliSeconds);
		return result;
	}

	private String generateToken() {
		String token = null;
		Timestamp currentTimeStamp = new Timestamp(new Date().getTime());
		token = new MD5Encrypter().encrypt(currentTimeStamp.toString()
				+ new Random().nextLong());
		return token;
	}

	public boolean verifyAccessToFolder(String token, long idFolder)
			throws TokenNotFoundException, UserNotFoundException,
			FolderNotFoundException {
		boolean result = false;
		Token4UploadService token4UploadService = new Token4UploadServiceImpl();
		Token4Upload tokenData = token4UploadService.getByToken(token);
		if (tokenData == null || !tokenData.isActive()) {
			throw new TokenNotFoundException();
		}
		UserService userService = new UserServiceImpl();
		User user = userService.get(tokenData.getIdUser());
		if (user == null || !user.getIsActivated()) {
			throw new UserNotFoundException();
		}
		FolderService folderService = new FolderServiceImpl();
		Folder folder = folderService.get(idFolder);
		if (folder == null) {
			throw new FolderNotFoundException();
		}
		if (folder.getIdUser() == user.getId()) {
			result = true;
		}
		return result;
	}
}
