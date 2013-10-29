package com.epam.lab.controller.services.token4auth;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import com.epam.lab.controller.dao.token4auth.Token4AuthDAO;
import com.epam.lab.controller.dao.token4auth.Token4AuthDAOImpl;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.exceptions.notfound.UserNotFoundException;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserService;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Auth;
import com.epam.lab.model.User;

public class Token4AuthServiceImpl extends AbstractServiceImpl<Token4Auth>
		implements Token4AuthService {

	private Token4AuthDAO tokenDAO = ((Token4AuthDAO) dao);

	public Token4AuthServiceImpl() {
		super(new Token4AuthDAOImpl());
	}

	public Token4Auth createToken(long idUser, long liveTime) {
		Token4Auth token4Upload = new Token4Auth();
		token4Upload.setIdUser(idUser);
		token4Upload.setDestroyDate(getDestroyDate(liveTime));
		do {
			token4Upload.setToken(generateToken());
		} while (insert(token4Upload) == 0); // if token has already in DB
		return tokenDAO.getByToken(token4Upload.getToken());
	}

	public Token4Auth getByToken(String token) {
		return tokenDAO.getByToken(token);
	}

	public int deleteNotActiveTokens() {
		return tokenDAO.deleteNotActiveTokens();
	}

	private Timestamp getDestroyDate(long liveTime) {
		long timeInMiliSeconds = liveTime * 1000;
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
		Token4AuthService token4UploadService = new Token4AuthServiceImpl();
		Token4Auth tokenData = token4UploadService.getByToken(token);
		if (tokenData == null || !tokenData.isActive()) {
			throw new TokenNotFoundException();
		}
		UserService userService = new UserServiceImpl();
		User user = userService.get(tokenData.getIdUser());
		if (user == null || !user.getIsActivated()) {
			throw new UserNotFoundException();
		}
		FolderService folderService = new FolderServiceImpl();
		Folder folder = null;
		if (idFolder == 0) {
			folder = folderService.getRoot(user.getId());
		} else {
			folder = folderService.get(idFolder);
		}
		if (folder == null) {
			throw new FolderNotFoundException();
		}
		if (folder.getIdUser() == user.getId()) {
			result = true;
		}
		return result;
	}
}