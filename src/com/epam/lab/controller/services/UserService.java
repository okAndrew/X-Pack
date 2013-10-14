package com.epam.lab.controller.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.model.User;

public class UserService {

	static Logger logger = Logger.getLogger(UserService.class);

	public static void insertUser(String login, String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		user.setIdTariff(1);
		user.setCapacity(0);
		user.setToken(null);
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		userDAOImpl.insert(user);
	}

	public static User getUser(String email, String password) {
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	public static List<User> getAllUsers() {
		List<User> users = null;
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		users = userDaoImpl.getAll();
		return users;
	}

	public static User getUserById(long id) {
		User user = null;
		user = new UserDAOImpl().get(id);
		return user;
	}

	public static User getUserByEmail(String email) {
		return new UserDAOImpl().getByEmail(email);
	}
	
	public static void updateUser(int userId, String userLogin, String userEmail, int userIdTariff,
			String userToken){
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		int result = userDaoImpl.updateUser(userId, userLogin, userEmail, userIdTariff, userToken);
		logger.info("User with id "+userId+" is updated. Number of updated rows: "+ result);
	}
	public void deleteFilesAndFolders(String [] rs, String [] rs2, long userId){
		if (rs != null && !rs[0].equals("")) {
			for (int i = 0; i < rs.length; i++) {
				FileService.delete(Long.parseLong(rs[i]));
			}
		}
		if (rs2 != null && !rs2[0].equals("")) {
			for (int i = 0; i < rs2.length; i++) {
				FolderService.delete(Long.parseLong(rs2[i]), userId);
			}
		}
	}

}