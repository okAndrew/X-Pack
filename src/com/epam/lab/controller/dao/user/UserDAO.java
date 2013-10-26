package com.epam.lab.controller.dao.user;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.User;

public interface UserDAO extends GenericDAO<User> {

	User getByEmail(String email);

	int setIsActivate(boolean state, long id);
	
	int setIsBanned(boolean state, long id);
	
	List<User> getBannedUsers();
}
