package com.epam.lab.controller.dao;

import com.epam.lab.model.User;

public interface UserDAO extends GenericDAO<User> {
	User getByEmail(String email);

	int updateUser(long userId, String userLogin, String userEmail,
			long userIdTariff, String userToken, boolean activated);
	
}
