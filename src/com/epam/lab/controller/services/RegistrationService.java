package com.epam.lab.controller.services;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.model.User;

public class RegistrationService {
	
	static Logger logger = Logger.getLogger(RegistrationService.class);

	public User checkEmail(String email){
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}
	
}
