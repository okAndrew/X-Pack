package com.epam.lab.controller.dao.payment;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {

	private DBQueryExecutor<Payment> queryExecutor = new DBQueryExecutor<Payment>();

	@Override
	public Payment get(long id) {
		return null;
	}

	@Override
	public List<Payment> getAll() {
		return null;
	}

	@Override
	public int insert(Payment object) {
		String sql = "INSERT INTO payments(user, tariff, price, date_created, date_end, status, available) VALUES(?, ?, ?, ?, ?, ?, ?)";
		return queryExecutor.executeUpdate(sql, object.getUser(),
				object.getTariff(), object.getPrice(), object.getDateCreated(),
				object.getDateEnd(), object.getStatus(), object.getAvailable());
	}

	@Override
	public int update(Payment object) {
		String sql = "UPDATE payments SET user = ?, tariff = ?, date_created = ?, date_end = ?, price = ?, status = ?, available = ?  WHERE id=?";
		return queryExecutor.executeUpdate(sql, object.getUser(),
				object.getTariff(), object.getDateCreated(),
				object.getDateEnd(), object.getPrice(), object.getStatus(),
				object.getAvailable(), object.getId());
	}

	@Override
	public int delete(long id) {
		return 0;
	}

	@Override
	public List<Payment> getPayByUserId(long id) {
		String sql = "SELECT * FROM payments WHERE user=?";
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class,
				sql, id);
		return resultList;
	}

	@Override
	public List<Payment> getPayByPeriod(long userId, Timestamp startDate,
			Timestamp endDate) {
		String sql = "SELECT * FROM payments WHERE user=? AND date_created BETWEEN ? AND ?;";
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class,
				sql, userId, startDate, endDate);
		return resultList;
	}

	@Override
	public Payment getByUserTime(long user, Timestamp time) {
		String sql = "SELECT * FROM payments WHERE user = ? AND date_created = ?";
		Payment result = queryExecutor.executeQuerySingle(Payment.class, sql,
				user, time);
		return result;
	}

	@Override
	public List<Payment> getEndedAvailablePays() {
		String sql = "SELECT * FROM payments WHERE date_end < CURRENT_TIMESTAMP() AND available = 1;";
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class,
				sql);
		return resultList;
	}

	@Override
	public int disableEndedPayments() {
		String sql = "UPDATE payments SET available = 0 WHERE date_end < CURRENT_TIMESTAMP() AND available = 1";
		return queryExecutor.executeUpdate(sql);
	}

	@Override
	public boolean canDisableUser(long id) {
		String sql = "SELECT * FROM payments WHERE date_end > CURRENT_TIMESTAMP() AND available = 1 AND user = ?";
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class,
				sql, id);

		if (resultList != null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Payment getCurrentPayment(long userId) {
		String sql = "SELECT * FROM payments WHERE user = ? AND date_created < curtime() AND date_end > curtime() and available = 1 ORDER BY date_created DESC";
		Payment result = queryExecutor.executeQuerySingle(Payment.class, sql,
				userId);
		return result;
	}

	@Override
	public boolean pay(String[] sql, Object[][] args) {
		return queryExecutor.executeTransaction(sql, args);
	}

	@Override
	public List<Payment> getAvailableUserPays(long userId) {
		String sql = "SELECT * FROM payments WHERE available = 1 AND user = ? ORDER BY date_created ASC;";
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class, sql, userId);
		return resultList;
	}
	
}
