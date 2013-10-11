package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.UserDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.User;

public class UserDAOImpl implements UserDAO {
	private DBQueryExecutor<User> queryExecutor = new DBQueryExecutor<User>();

	@Override
	public User get(long id) {
		String sql = "SELECT * FROM users WHERE id=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, id);
		return result;
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM users";
		List<User> resultList = queryExecutor.executeQuery(User.class, sql);
		return resultList;
	}

	@Override
	public int insert(User user) {
		String sql = "INSERT INTO users(login, email, password, id_tariff, capacity, token) VALUES (?, ?, ?, ?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, user.getLogin(),
				user.getEmail(), user.getPassword(), user.getIdTariff(),
				user.getCapacity(), user.getToken());
		return result;
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE users SET login=? email=?, password=?, id_tariff=?, capacity=?, token=?) WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, user.getLogin(), user.getEmail(),
				user.getPassword(), user.getIdTariff(), user.getCapacity(),
				user.getToken(), user.getId());
		return result;
	}

	@Override
	public int updateUser(int userId, String userLogin, String userEmail,
			int userIdTariff, String userToken) {
		String sql = "UPDATE users SET login=?, email=?, id_tariff=?, token=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, userLogin, userEmail, userIdTariff,
				userToken, userId);
		return result;

	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM users WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

	@Override
	public User getByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, email);
		return result;
	}

}
