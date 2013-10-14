package com.epam.lab.controller.dao;

import com.epam.lab.model.User;

public interface UserDAO extends GenericDAO<User> {
	User getByEmail(String email);

	int updateUser(int userId, String userLogin, String userEmail,
			int userIdTariff, String userToken);
	
	int activateUser(int id);
	
	int activateUser(User user);
	
}
