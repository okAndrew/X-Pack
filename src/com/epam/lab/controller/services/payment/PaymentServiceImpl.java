package com.epam.lab.controller.services.payment;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Payment;

public class PaymentServiceImpl extends AbstractServiceImpl<Payment> implements
		PaymentService {
	static Logger logger = Logger.getLogger(PaymentServiceImpl.class);
	private PaymentDAOImpl paymentDAOImpl = (PaymentDAOImpl) dao;

	public PaymentServiceImpl() {
		super(new PaymentDAOImpl());
	}

	public List<Payment> getAllPayByUserId(long id) {
		List<Payment> list = paymentDAOImpl.getPayByUserId(id);
		return list;
	}

	public List<Payment> getPayByPeriod(long userId, String startdateRequest,
			String enddateRequest) {
		List<Payment> list = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp endDate = null;
		Timestamp startDate = null;
		if (startdateRequest != "") {
			if (enddateRequest != "") {
				try {
					endDate = new Timestamp(sf.parse(enddateRequest).getTime());
				} catch (ParseException e) {
					logger.error(e);
				}
			} else {
				endDate = TimeStampManager.getFormatCurrentTimeStamp();
			}
			try {
				startDate = new Timestamp(sf.parse(startdateRequest).getTime());
			} catch (ParseException e) {
				logger.error(e);
			}
			list = paymentDAOImpl.getPayByPeriod(userId, startDate, endDate);

		}
		return list;
	}

	public Payment getCurrentPayment(long userId) {
		return paymentDAOImpl.getCurrentPayment(userId);
	}

	public List<Payment> getAvailableUserPays(long userId) {
		return paymentDAOImpl.getAvailableUserPays(userId);
	}

	public List<Payment> getAvailableEndedPayments() {
		return paymentDAOImpl.getAvailableEndedPays();
	}

	public Payment getLastUserPayment(long userId) {
		return paymentDAOImpl.getLastUserPayment(userId);
	}

}