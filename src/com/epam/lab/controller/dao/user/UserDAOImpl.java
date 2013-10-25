package com.epam.lab.controller.dao.user;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
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
		String sql = "INSERT INTO users(login, email, password, id_tariff, capacity, id_role) VALUES (?, ?, ?, ?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, user.getLogin(),
				user.getEmail(), user.getPassword(), user.getIdTariff(),
				user.getCapacity(), user.getRole().getNumber());
		return result;
	}

	@Override
	public int update(User user) {
		String sql = "UPDATE users SET login=?, email=?, password=?, id_tariff=?, capacity=?, is_activated=?, id_role=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, user.getLogin(), user
				.getEmail(), user.getPassword(), user.getIdTariff(), user
				.getCapacity(), user.getIsActivated(), user.getRole()
				.getNumber(), user.getId());

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

	@Override
	public int deaktivatedUserById(long id) {
		String sql = "UPDATE users SET is_activated=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, false, id);
	}

	@Override
	public int activatedUserById(long id) {
		String sql = "UPDATE users SET is_banned=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, false, id);
	}

	public boolean checkEmailById(String email, long userId) {
		String sql = "SELECT * FROM users WHERE email=? AND id!=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, email,
				userId);
		if (result == null) {
			return true;
		}
		return false;
	}

	public boolean ckeckLoginById(String login, long userId) {
		String sql = "SELECT * FROM users WHERE login=? AND id!=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, login,
				userId);
		if (result == null) {
			return true;
		}
		return false;
	}

}