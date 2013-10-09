package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.PaymentDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

public class PaymentDAOImpl implements PaymentDAO {
	private DBQueryExecutor<Payment> queryExecutor = new DBQueryExecutor<Payment>();

	public static final String INSERT_VALUES = "INSERT INTO payments(description, status, date, id_user, available) VALUES (?,?,?,?,?) ";
	public static final String DELETE_VALUES = "DELETE FROM payments WHERE id=?";
	public static final String SELECT_VALUES = "SELECT id, description, status, date, id_user, available   FROM payments";
	public static final String SELECT_VALUES_BY_ID = "SELECT id, description, status, date, id_user, available FROM payments WHERE id=?";

	@Override
	public Payment get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Payment object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Payment object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payment> getPayByUserId(long id) {
		String sql = "SELECT * FROM payments WHERE id_user=?";
		List<Payment> resultList = queryExecutor.executeQuery(User.class, sql,
				id);
		return resultList;
	}

}
