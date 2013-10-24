package com.epam.lab.controller.dao.payment;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Payment;

public interface PaymentDAO extends GenericDAO<Payment> {
	
	List<Payment> getPayByUserId(long id);

	List<Payment> getPayByPeriod(long userId, Timestamp startDate,
			Timestamp endDate);
	
	Payment getByUserTime(long user, Timestamp time);
	
	List<Payment> getEndedAvailablePays();
	
	int disableEndedPayments();
	
	boolean canDisableUser(long id);
	
	Payment getCurrentPayment(long userId);
	
}
