package com.epam.lab.controller.services;

import java.sql.Timestamp;

import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.MailSender;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Token;
import com.epam.lab.model.User;

public class SendRestorePassMail {
	
	public String send(String email) {
		String msg = null;
		UserServiceImpl userService = new UserServiceImpl();
		User user = userService.get(email);
		
		if (user != null) {
			TokenDAOImpl tokenDAO = new TokenDAOImpl();
			Timestamp curTime = TimeStampManager.getCurrentTime();
			String tokenStr = new MD5Encrypter().encrypt(user.getEmail() + curTime);
			String mail = "" + tokenStr;
			
			Token token = new Token();
			token.setDate(curTime);
			token.setIdUser(user.getId());
			token.setAvailable(true);
			token.setToken(tokenStr);
			tokenDAO.insert(token);
			
			MailSender.send(user.getEmail(), "Restore", mail);
		} else {
			msg = "Error";
		}
		
		return msg;
	}

}
