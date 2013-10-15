package com.epam.lab.controller.services;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import sun.rmi.runtime.NewThreadAction;

import com.epam.lab.controller.dao.impl.TokenDAOImpl;
import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.utils.CurrentTimeStamp;
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
				UserService userService = new UserService();
				userService.addUser(login, email, md5.encrypt(password));
				User user = userService.getUserByEmail(email);
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
		FolderService folderService = new FolderService();
		folderService.createRootFolder(user.getId());
	}
	
	public void sendActivations(User user) {
		String token = createToken(user);
		MailSender.send(user.getEmail(), "Activation", token);
	}
	
	private String createToken(User user) {
		Token token = new Token();
		MD5Encrypter md5 = new MD5Encrypter();
		String sToken = null;
		Timestamp timestamp = CurrentTimeStamp.getCurrentTimeStamp();
		
		sToken = md5.encrypt(user.getEmail() + timestamp);
		
		token.setUser(user.getId());
		token.setDate(timestamp);
		token.setToken(sToken);
		
		new TokenDAOImpl().insert(token);

		return sToken;
	}
	
	public int activateUser(String email, String token) {
		int result = 0;
		
		UserService userService = new UserService();
		TokenDAOImpl tokenDAOImpl = new TokenDAOImpl();
		
		User user = userService.getUserByEmail(email);
		Token tokenObj = tokenDAOImpl.get(token);

		if (tokenObj.getUser() == user.getId() && tokenObj.getAvailable()) {
			tokenDAOImpl.deactivateToken(token);
			userService.activateUser(user);
		}
		
		return result;
	}

}