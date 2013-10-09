package com.epam.lab.controller.services;

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
}