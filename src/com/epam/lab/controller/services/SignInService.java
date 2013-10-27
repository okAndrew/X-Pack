package com.epam.lab.controller.services;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.User;

public class SignInService {

	static Logger logger = Logger.getLogger(RegistrationService.class);
	
	public User signIn(String email, String password) {
		User user = null;
		
		if (checkParam(email, password)) {
			UserServiceImpl userService = new UserServiceImpl();
			MD5Encrypter md5 = new MD5Encrypter();
			user = userService.get(email, md5.encrypt(password));
		}
		
		return user;
	}
	
	private boolean checkParam(String email, String password) {
		boolean bEmail = Validator.USER_EMAIL.validate(email);
		boolean bPass = Validator.USER_PASSWORD.validate(password);
		
		return bEmail && bPass;
	}
}
