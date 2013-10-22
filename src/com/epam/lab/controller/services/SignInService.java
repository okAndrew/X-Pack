package com.epam.lab.controller.services;

import org.apache.log4j.Logger;

import com.epam.lab.controller.utils.Validator;

public class SignInService {

	static Logger logger = Logger.getLogger(RegistrationService.class);
	
	public String signIn(String email, String password) {
		String msg = null;
		
		if (checkParam(email, password)) {
			
		} else {
			msg = "Check email/password";
		}
		
		return msg;
	}
	
	private boolean checkParam(String email, String password) {
		boolean bEmail = Validator.USER_EMAIL.validate(email);
		boolean bPass = Validator.USER_PASSWORD.validate(password);
		
		return bEmail && bPass;
	}
}
