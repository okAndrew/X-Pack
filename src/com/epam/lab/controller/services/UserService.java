package com.epam.lab.controller.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.PaymentDAOImpl;
import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

public class UserService {

	static Logger logger = Logger.getLogger(UserService.class);

	public void insertUser(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setIdTariff(1);
		user.setCapacity(0);
		user.setToken(null);
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		userDAOImpl.insert(user);
	}

	public User getUser(String email, String password) {
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	public List<User> getAllUsers(){
		List<User> users = null;
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		users = userDaoImpl.getAll();
		return users;
	}

	public User getUserById(long id) {
		User user = null;
		user = new UserDAOImpl().get(id);
		return user;
	}

}