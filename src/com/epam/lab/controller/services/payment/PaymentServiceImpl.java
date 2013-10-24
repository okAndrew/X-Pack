package com.epam.lab.controller.services.payment;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;

public class PaymentServiceImpl extends AbstractServiceImpl<Payment> implements
		PaymentService {
	static Logger logger = Logger.getLogger(PaymentServiceImpl.class);

	public PaymentServiceImpl() {
		super(new PaymentDAOImpl());
	}

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
	
	public Payment getCurrentPayment(long userId) {
		return new PaymentDAOImpl().getCurrentPayment(userId);
	}

	public void pay(long tariffId, int months, long userId) {
		PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
		UserServiceImpl userService = new UserServiceImpl();
		TariffServiseImpl tariffService = new TariffServiseImpl();
		Timestamp time = TimeStampManager.getCurrentTime();
		
		Tariff newTariff = tariffService.get(tariffId);
		User user = userService.get(userId);
		Payment payment = getCurrentPayment(user.getId());
		
		if (payment != null) {
			
			int daysBetween = TimeStampManager.daysBetween(payment.getDateCreated(), payment.getDateEnd());
			int daysLeft = TimeStampManager.daysBetween(TimeStampManager.getCurrentTime(), payment.getDateEnd());
			double savedCash = Math.round((payment.getPrice() / daysBetween * daysLeft) * 100.0) / 100.0;
			
			Timestamp dateEnd = TimeStampManager.getCurrentTime();
			TimeStampManager.addNumberOfMonth(dateEnd, months);
			double price = newTariff.getPrice() * months - savedCash;
			
			payment.setDateEnd(time);
			payment.setAvailable(false);
			paymentDAO.update(payment);
			Payment newPayment = createPayment(user.getId(), newTariff.getId(), time, dateEnd, price, true, true);
			user.setIdTariff(newTariff.getId());
			userService.update(user);
		}
	}
	
	private Payment createPayment(long userId, long tariffId, Timestamp dateCreated, Timestamp dateEnded, double price, boolean status, boolean available) {
		PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
		Payment payment = new Payment();
		
		payment.setUser(userId);
		payment.setTariff(tariffId);
		payment.setDateCreated(dateCreated);
		payment.setDateEnd(dateEnded);
		payment.setPrice(price);
		payment.setStatus(status);
		payment.setAvailable(available);
		
		paymentDAO.insert(payment);
		
		return payment;
	}

}