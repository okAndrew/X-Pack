package com.epam.lab.controller.dao.user;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Counter;
import com.epam.lab.model.User;

public class UserDAOImpl implements UserDAO {
	private DBQueryExecutor<User> queryExecutor = new DBQueryExecutor<User>();

	@Override
	public User get(long id) {
		String sql = "SELECT users.id, users.login, users.email, users.password, "
				+ "users.id_tariff, users.capacity, users.is_activated, "
				+ "users.is_banned, users.id_role, locale.locale as last_locale "
				+ "FROM users left join locale on users.last_locale=locale.id WHERE users.id=?;";
		User result = queryExecutor.executeQuerySingle(User.class, sql, id);
		return result;
	}

	@Override
	public long getCount() {
		String sql = "SELECT COUNT(id) AS countUsers FROM users";
		Counter counter = new DBQueryExecutor<Counter>().executeQuerySingle(
				Counter.class, sql);
		return counter.getCountUsers();
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM users";
		List<User> resultList = queryExecutor.executeQuery(User.class, sql);
		return resultList;
	}

	@Override
	public List<User> getBySQL(String sql) {
		return queryExecutor.executeQuery(User.class, sql);
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
		String sql = "UPDATE users SET login=?, email=?, password=?, id_tariff=?, capacity=?, is_activated=?, is_banned = ?, id_role=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, user.getLogin(),
				user.getEmail(), user.getPassword(), user.getIdTariff(),
				user.getCapacity(), user.getIsActivated(), user.getIsBanned(),
				user.getRole().getNumber(), user.getId());

		return result;
	}

	@Override
	public int delete(long id) {
		String sql = "UPDATE users SET is_activated=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, false, id);
		return result;
	}

	@Override
	public User getByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, email);
		return result;
	}

	@Override
	public User getByLogin(String login) {
		String sql = "SELECT * FROM users WHERE login=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, login);
		return result;
	}

	@Override
	public boolean checkEmailById(String email, long userId) {
		String sql = "SELECT * FROM users WHERE email=? AND id!=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, email,
				userId);
		if (result == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean ckeckLoginById(String login, long userId) {
		String sql = "SELECT * FROM users WHERE login=? AND id!=?";
		User result = queryExecutor.executeQuerySingle(User.class, sql, login,
				userId);
		if (result == null) {
			return true;
		}
		return false;
	}

	@Override
	public int setIsActivate(boolean state, long id) {
		String sql = "UPDATE users SET is_activated=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, state, id);
	}

	@Override
	public int setIsBanned(boolean state, long id) {
		String sql = "UPDATE users SET is_banned=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, state, id);
	}

	@Override
	public List<User> getBannedUsers() {
		String sql = "SELECT * FROM users WHERE is_banned = 1";
		List<User> resultList = queryExecutor.executeQuery(User.class, sql);
		return resultList;
	}

	@Override
	public int setLastLocale(long locId, long userId) {
		String sql = "UPDATE users SET last_locale=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, locId, userId);

	}

}