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
		MailSender sender = new MailSender();
		
		if (user != null) {
			TokenDAOImpl tokenDAO = new TokenDAOImpl();
			Timestamp curTime = TimeStampManager.getCurrentTime();
			String tokenStr = new MD5Encrypter().encrypt(user.getEmail() + curTime);
			String mail = "http://localhost:8080/dreamhost/RestorePassword?token=" + tokenStr;
			
			Token token = new Token();
			token.setDate(curTime);
			token.setIdUser(user.getId());
			token.setToken(tokenStr);
			tokenDAO.insert(token);
			
			sender.send(user.getEmail(), "Restore", mail);
		} else {
			msg = "Error";
		}
		
		return msg;
	}

}
