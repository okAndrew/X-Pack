package com.epam.lab.controller.services;

import java.util.List;

import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.model.Payment;

public class UserControlService {

	public long[] disableEndedPayments() {
		PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
		long[] usersId;
		
		List<Payment> list = paymentDAO.getEndedAvailablePays();
		usersId = new long[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			usersId[i] = list.get(i).getUser();
		}
		
		paymentDAO.disableEndedPayments();
		
		return usersId;
	}
	
	public void checkUsers(long[] usersId) {
		PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
		
		for (int i = 0; i < usersId.length; i++) {
			if(paymentDAO.canDisableUser(usersId[i])) {
				
			}
		}
	}
	
}
