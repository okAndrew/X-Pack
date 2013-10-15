package com.epam.lab.controller.services;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.utils.MD5Encrypter;
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
				createRootFolder(email);
			} else {
				result = "User with this email is alredy registered";
			}
		} else {
			result = "Fields cannot be null";
		}
		
		return result;
	}
	
	private void createRootFolder(String email) {
		UserService userService = new UserService();
		User user = userService.getUserByEmail(email);
		FolderService.createRootFolder((int)(user.getId()));
	}
	
	public int activateUser(String email, String code) {
		int result = 0;
		
		UserService userService = new UserService();
		User user = userService.getUserByEmail(email);
		
		return result;
	}

}