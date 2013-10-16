package com.epam.lab.controller.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.PaymentDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Payment;

public class PaymentDAOImpl implements PaymentDAO {
	private DBQueryExecutor<Payment> queryExecutor = new DBQueryExecutor<Payment>();

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
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class,
				sql, id);
		return resultList;
	}

	@Override
	public List<Payment> getPayByPeriod(long userId, Timestamp startDate,
			Timestamp endDate) {
		String sql = "SELECT * FROM payments WHERE id_user=? AND date BETWEEN ? AND ?;";
		List<Payment> resultList = queryExecutor.executeQuery(Payment.class,
				sql, userId, startDate, endDate );
		return resultList;
	}

}
