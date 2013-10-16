package com.epam.lab.controller.services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.PaymentDAOImpl;
import com.epam.lab.model.Payment;

public class PaymentService {

	static Logger logger = Logger.getLogger(PaymentService.class);

	public List<Payment> getAllPayByUserId(long id) {
		List<Payment> list = new PaymentDAOImpl().getPayByUserId(id);
		return list;
	}

	public List<Payment> getPayByPeriod(long userId, Timestamp startDate,
			Timestamp endDate) {
		List<Payment> list = new PaymentDAOImpl().getPayByPeriod(userId,
				startDate, endDate);
		return list;
	}
}