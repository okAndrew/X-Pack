package com.epam.lab.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.UserService;
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
				UserService userService = new UserService();
				userService.insertUser(login, email, getChecksum(password));
			} else {
				result = "User with this email is alredy registered";
			}
		} else {
			result = "Fields cannot be null";
		}
		
		return result;
	}
	
	public int activateUser(String email, String code) {
		int result = 0;
		
		UserService userService = new UserService();
		User user = userService.getUserByEmail(email);
		
		return result;
	}
	
	private String getChecksum(String text) {
		String hashPass = null;
		
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(text.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashPass = bigInt.toString(16);
			
			while(hashPass.length() < 32 ){
				hashPass = "0" + hashPass;
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			return null;
		}
		
		return hashPass;
	}

}