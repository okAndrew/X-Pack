package com.epam.lab.controller.dao;

import com.epam.lab.model.User;

public interface UserDAO extends GenericDAO<User> {

	User getByEmail(String email);

	int deaktivatedUserById(long id);

	int activatedUserById(long id);

}
