package com.epam.lab.controller.services;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.AdminDAOImpl;
import com.epam.lab.model.Admin;

public class AdminService {
	
	private static final Logger logger = Logger.getLogger(AdminService.class);
	
	public Admin getAdminByLogin(String login){
		Admin admin = null;
		AdminDAOImpl adminDaoImpl = new AdminDAOImpl();
		admin = adminDaoImpl.getByLogin(login);
		if (admin == null){
			logger.info("Admin " + login + " don't forget in DB!!!");
		}
		return admin;
	}
	
	public Boolean validate(String login, String password){
		Boolean result = false;
		Admin admin = getAdminByLogin(login);
		if (admin != null && password.equals(admin.getPassword())){
			result = true;
		}
		return result;
	}
	
}
