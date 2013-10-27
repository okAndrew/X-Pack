package com.epam.lab.controller.services;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.dao.user.UserDAOImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.MailSender;
import com.epam.lab.model.Token;
import com.epam.lab.model.User;

public class RegistrationService {

	static Logger logger = Logger.getLogger(RegistrationService.class);

	public User checkEmail(String email) {
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public String chechParams(String login, String email, String password) {
		String result = null;

		if (login == null || login == "" || email == null || email == ""
				|| password == null || password == "") {
			result = "Fields cannot be empty";
		}

		return result;
	}

	public String regUser(String login, String email, String password) {
		String result = chechParams(login, email, password);

		if (result == null) {
			if (checkEmail(email) == null) {
				MD5Encrypter md5 = new MD5Encrypter();
				UserServiceImpl userService = new UserServiceImpl();
				userService.addUser(login, email, md5.encrypt(password));
				User user = userService.get(email);
				createRootFolder(user);
				sendActivations(user);
			} else {
				result = "User with this email is alredy registered";
			}
		} else {
			result = "Fields cannot be null";
		}

		return result;
	}

	private void createRootFolder(User user) {
		FolderServiceImpl folderService = new FolderServiceImpl();
		folderService.createRoot(user.getId());
	}

	public void sendActivations(User user) {
		String token = createToken(user);
		String msg = "http://localhost:8080/dreamhost/activation?token=" + token;
		String head = "Activation";

		MailSender.send(user.getEmail(), head, msg);
	}

	private String createToken(User user) {
		Token token = new Token();
		MD5Encrypter md5 = new MD5Encrypter();
		String sToken = null;
		Timestamp timestamp = TimeStampManager.getFormatCurrentTimeStamp();

		sToken = md5.encrypt(user.getEmail() + timestamp);

		token.setIdUser(user.getId());
		token.setDate(timestamp);
		token.setToken(sToken);

		new TokenDAOImpl().insert(token);

		return sToken;
	}

	public boolean activateUser(String token) {
		boolean result = false;

		UserServiceImpl userService = new UserServiceImpl();
		TokenDAOImpl tokenDAOImpl = new TokenDAOImpl();
		
		logger.debug(token);

		Token tokenObj = tokenDAOImpl.get(token);
		User user = userService.get(tokenObj.getIdUser());
		
		logger.debug(user);
		logger.debug(tokenObj);

		if (tokenObj.getAvailable()) {
			userService.activateUser(user);
			tokenObj.setAvailable(false);
			tokenDAOImpl.update(tokenObj);
			result = true;
		}

		return result;
	}

}