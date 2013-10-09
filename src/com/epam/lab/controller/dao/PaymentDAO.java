package com.epam.lab.controller.dao;

import java.util.List;

import com.epam.lab.model.Payment;

public interface PaymentDAO extends GenericDAO<Payment> {
	List<Payment> getPayByUserId(long id);
}
