package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.UserDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.User;

public class UserDAOImpl implements UserDAO {
	private DBQueryExecutor<User> queryExecutor = new DBQueryExecutor<User>();

	public static final String INSERT_VALUES = "INSERT INTO users(login, email, password, id_tariff, capacity, token) VALUES (?, ?, ?, ?, ?, ?) ";
	public static final String DELETE_VALUES = "DELETE FROM users WHERE id = ?";
	public static final String SELECT_VALUES = "SELECT id, email, password, id_tariff, capacity, token FROM users";
	public static final String SELECT_VALUES_BY_ID = "SELECT id, email, password, id_tariff, capacity, token FROM users WHERE id = ?";

	@Override
	public User get(long id) {
		String sql = "SELECT * FROM users WHERE id=?";
		List<User> resultList = queryExecutor.executeQuery(User.class, sql, id);
		if (resultList == null || resultList.isEmpty())
			return null;
		else {
			return resultList.get(0);
		}
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
		int result = queryExecutor.executeUpdate(sql, user.getLogin(), user.getEmail(),
				user.getPassword(), user.getIdTariff(), user.getCapacity(),
				user.getToken());
		return result;
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE users SET email=?, password=?, id_tariff=?, capacity=?, token=?) WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, user.getEmail(),
				user.getPassword(), user.getIdTariff(), user.getCapacity(),
				user.getToken(), user.getId());
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
		List<User> resultList = queryExecutor.executeQuery(User.class, sql,
				email);
		if (resultList == null || resultList.isEmpty())
			return null;
		else {
			return resultList.get(0);
		}
	}
}
