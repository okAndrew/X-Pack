package com.epam.lab.controller.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.User;

public class UserService {

	static Logger logger = Logger.getLogger(UserService.class);

	public void addUser(String login, String email, String password) {
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

	public User getUser(String email, String password) {
		User user = null;
		user = new UserDAOImpl().getByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	public List<User> getAllUsers() {
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

	public User getUserByEmail(String email) {
		return new UserDAOImpl().getByEmail(email);
	}

	public int updateUser(User user) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		int result = userDaoImpl.update(user);
		return result;
	}

	public void deleteFilesAndFolders(String[] rs, String[] rs2, long userId) {
		FolderService service = new FolderService();
		FileService service2 = new FileService();
		if (rs != null && !rs[0].equals("")) {
			for (int i = 0; i < rs.length; i++) {
				service2.delete(Long.parseLong(rs[i]));
			}
		}
		if (rs2 != null && !rs2[0].equals("")) {
			for (int i = 0; i < rs2.length; i++) {
				service.delete(Long.parseLong(rs2[i]), userId);
			}
		}
	}

	public String deleteUsers(String[] usersId) {
		String errorMessage = null;
		if (usersId == null) {
			errorMessage = "Please check the users you want to delete!!!";
		} else {
			for (int i = 0; i < usersId.length; i++) {
				deleteUser(Long.parseLong(usersId[i]));
			}
		}
		return errorMessage;
	}

	public void deleteUser(Long id) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		userDaoImpl.delete(id);
	}

	public int activateUser(User user) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		int result = userDaoImpl.activatedUserById(user.getId());
		return result;
	}

	public int deactivateUser(User user) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		int result = userDaoImpl.deaktivatedUserById(user.getId());
		return result;
	}

	public String deactivateUsers(String[] usersId) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		String errorMessage = null;
		if (usersId == null) {
			errorMessage = "Please check the users you want to delete!!!";
		} else {
			for (int i = 0; i < usersId.length; i++) {
				userDaoImpl.deaktivatedUserById(Long.parseLong(usersId[i]));
			}
		}
		return errorMessage;
	}

	public String activateUsers(String[] usersId) {
		UserDAOImpl userDaoImpl = new UserDAOImpl();
		String errorMessage = null;
		if (usersId == null) {
			errorMessage = "Please check the users you want to delete!!!";
		} else {
			for (int i = 0; i < usersId.length; i++) {
				userDaoImpl.activatedUserById(Long.parseLong(usersId[i]));
			}
		}
		return errorMessage;
	}

	public void changeUserPassword(User user, String password) {
		MD5Encrypter md5 = new MD5Encrypter();
		String md5Pass = md5.encrypt(password);
		UserDAOImpl userDaoImpl = new UserDAOImpl();

		user.setPassword(md5Pass);
		userDaoImpl.update(user);
	}

}