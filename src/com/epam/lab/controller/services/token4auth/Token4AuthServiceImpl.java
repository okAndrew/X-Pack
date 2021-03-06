package com.epam.lab.controller.services.token4auth;

import java.sql.Timestamp;
import java.util.Date;

import com.epam.lab.controller.dao.token4auth.Token4AuthDAO;
import com.epam.lab.controller.dao.token4auth.Token4AuthDAOImpl;
import com.epam.lab.controller.exceptions.notfound.TokenNotFoundException;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Token4Auth;
import com.epam.lab.model.User;

public class Token4AuthServiceImpl extends AbstractServiceImpl<Token4Auth>
		implements Token4AuthService {

	private Token4AuthDAO tokenDAO = ((Token4AuthDAO) dao);

	public Token4AuthServiceImpl() {
		super(new Token4AuthDAOImpl());
	}

	@Override
	public Token4Auth createToken(long idUser, long liveTime) {
		Token4Auth token4Upload = new Token4Auth();
		token4Upload.setIdUser(idUser);
		token4Upload.setDestroyDate(getDestroyDate(liveTime));
		do {
			token4Upload.setToken(new MD5Encrypter().generateRandomHash());
		} while (insert(token4Upload) == 0); // if token has already in DB
		return tokenDAO.getByToken(token4Upload.getToken());
	}

	@Override
	public Token4Auth getByToken(String token) {
		Token4Auth token4Auth = tokenDAO.getByToken(token);
		if (token4Auth != null && isActive(token4Auth))
			return token4Auth;
		else {
			return null;
		}
	}

	@Override
	public boolean isActive(Token4Auth tokenData) {
		return tokenData.getDestroyDate().after(
				TimeStampManager.getCurrentTime());
	}

	@Override
	public int deleteNotActiveTokens() {
		return tokenDAO.deleteNotActiveTokens();
	}

	@Override
	public Folder verifyAccessRequest(String token, long idFolder)
			throws TokenNotFoundException {
		Folder folder = null;
		Token4AuthService service = new Token4AuthServiceImpl();
		Token4Auth tokenData = service.getByToken(token);
		if (tokenData != null && isActive(tokenData)) {
			User user = new UserServiceImpl().get(tokenData.getIdUser());
			if (user != null) {
				FolderServiceImpl folderService = new FolderServiceImpl();
				if (idFolder == 0) {
					folder = folderService.getRoot(tokenData.getIdUser());
				} else {
					folder = folderService.get(idFolder);
					if (folder.getIdUser() != user.getId()) {
						folder = null;
					}
				}
			} else {
				throw new TokenNotFoundException();
			}
		} else {
			throw new TokenNotFoundException();
		}
		return folder;
	}

	private Timestamp getDestroyDate(long liveTime) {
		long timeInMiliSeconds = liveTime * 1000;
		Timestamp result = new Timestamp(new Date().getTime()
				+ timeInMiliSeconds);
		return result;
	}
}