package com.epam.lab.controller.services;

import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Token;
import com.epam.lab.model.User;

public class RestorePassword {

	public String restore(String token, String email, String password) {
		String msg = null;
		
		if (!Validator.MD5_CHECKSUM.validate(token) ||
				!Validator.USER_EMAIL.validate(email) ||
				!Validator.USER_PASSWORD.validate(password)) {
				
			msg = "Incorrect params";
			return msg;
		}
		
		UserServiceImpl userService = new UserServiceImpl();
		TokenDAOImpl tokenDAO = new TokenDAOImpl();
		User user = userService.get(email);
		Token tokenObj = tokenDAO.get(token);
		MD5Encrypter md5 = new MD5Encrypter();
			
		if (user != null && tokenObj != null && tokenObj.getIdUser() == user.getId()) {
			tokenObj.setAvailable(false);
			user.setPassword(md5.encrypt(password));
			userService.update(user);
			tokenDAO.update(tokenObj);
		}
		
		return msg;
	}
	
}
