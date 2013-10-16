package com.epam.lab.controller.dao;

import com.epam.lab.model.User;

public interface UserDAO extends GenericDAO<User> {

	User getByEmail(String email);

	int updateUser(long userId, String userLogin, String userEmail, String password,
			long userIdTariff, int capacity, String userToken, boolean activated);

	int deaktivatedUserById(long id);

	int activatedUserById(long id);

}
