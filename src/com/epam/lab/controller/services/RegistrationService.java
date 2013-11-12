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
import com.epam.lab.controller.utils.Validator;
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

	public User checkLogin(String login) {
		User user = null;
		user = new UserDAOImpl().getByLogin(login);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public String chechParams(String login, String email, String password) {
		String result = null;

		if (!Validator.USER_LOGIN.validate(login)) {
			result = "Your_login_format_filed";
		} else if (!Validator.USER_EMAIL.validate(email)) {
			result = "Your_email_format_filed";
		} else if (!Validator.USER_PASSWORD.validate(password)) {
			result = "Your_password_format_filed";
		}
		return result;
	}

	public String regUser(String login, String email, String password) {
		String result = chechParams(login, email, password);

		if (result == null) {
			if (checkEmail(email) == null) {
				if (checkLogin(login) == null) {
					MD5Encrypter md5 = new MD5Encrypter();
					UserServiceImpl userService = new UserServiceImpl();
					userService.addUser(login, email, md5.encrypt(password));
					User user = userService.get(email);
					createRootFolder(user);
					sendActivations(user);
				} else {
					result = "User_with_this_login_is_alredy_registered";
				}

			} else {
				result = "User_with_this_email_is_alredy_registered";
			}
		} else {
			result = "Fields_cannot_be_null";
		}
		return result;
	}

	private void createRootFolder(User user) {
		FolderServiceImpl folderService = new FolderServiceImpl();
		folderService.createRoot(user.getId());
	}

	public void sendActivations(User user) {
		String token = createToken(user);
		MailSender sender = new MailSender();
		String msg = "http://localhost:8080/dreamhost/activation?token="
				+ token;
		String head = "Activation";
		sender.send(user.getEmail(), head, msg);
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
		boolean result = true;

		UserServiceImpl userService = new UserServiceImpl();
		TokenDAOImpl tokenDAOImpl = new TokenDAOImpl();

		logger.debug(token);

		Token tokenObj = tokenDAOImpl.get(token);
		User user = userService.get(tokenObj.getIdUser());

		logger.debug(user);
		logger.debug(tokenObj);

		userService.activateUser(user);

		return result;
	}
}