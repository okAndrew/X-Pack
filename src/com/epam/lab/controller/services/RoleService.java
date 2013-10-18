package com.epam.lab.controller.services;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.RoleDAOImpl;
import com.epam.lab.model.Role;

public class RoleService {

	static Logger logger = Logger.getLogger(RoleService.class);

	public Role getRoleById(long id) {
		Role role = null;
		role = new RoleDAOImpl().get(id);
		return role;
	}

}