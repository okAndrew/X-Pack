package com.epam.lab.controller.services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.PaymentDAOImpl;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;

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
	
	public void createPayment(User user, Tariff tariff) {
		PaymentDAOImpl paymentDAOImpl = new PaymentDAOImpl();
		Payment payment = new Payment();
		Timestamp time = CurrentTimeStamp.getCurrentTimeStamp();
		
		payment.setUser(user.getId());
		payment.setTariff(tariff.getId());
		payment.setPrice(tariff.getPrice());
		payment.setDateCreated(time);
		
		paymentDAOImpl.insert(payment);
		payment = paymentDAOImpl.getByUserTime(user.getId(), time);
		
		activatePayment(payment);
		
		user.setIdTariff(tariff.getId());
		new UserService().updateUser(user);
	}
	
	public int activatePayment(Payment payment) {
		Timestamp dateEnd = CurrentTimeStamp.addMonth(payment.getDateCreated());
		payment.setStatus(true);
		payment.setDateEnd(dateEnd);
		
		if (CurrentTimeStamp.getCurrentTimeStamp().before(dateEnd)) {
			payment.setAvailable(true);
		}
		
		return new PaymentDAOImpl().update(payment);
	}
	
}