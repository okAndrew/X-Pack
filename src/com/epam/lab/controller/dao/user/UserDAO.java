package com.epam.lab.controller.dao.user;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.User;

public interface UserDAO extends GenericDAO<User> {

	User getByEmail(String email);

	int deaktivatedUserById(long id);

	int activatedUserById(long id);

}
