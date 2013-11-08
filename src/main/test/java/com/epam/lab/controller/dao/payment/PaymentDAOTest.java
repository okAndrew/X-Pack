package main.test.java.com.epam.lab.controller.dao.payment;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Payment;

public class PaymentDAOTest {
	private static PaymentDAOImpl paymentDao = new PaymentDAOImpl();
	private static Payment payment = new Payment();
	private static Timestamp dateStart = null;
	private static Timestamp dateEnd = null;

	@BeforeClass
	public static void setLocale() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1, date2;
		try {
			date1 = sdf.parse("2013-11-07 02:18:09");
			date2 = sdf.parse("2014-11-07 02:18:09");
			dateStart = new Timestamp(date1.getTime());
			dateEnd = new Timestamp(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		payment.setId(1);
		payment.setUser(83);
		payment.setTariff(3);
		payment.setDateCreated(dateStart);
		payment.setDateEnd(dateEnd);
		payment.setPrice(59.88);
		payment.setStatus(true);
		payment.setAvailable(true);
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGet() {
		assertEquals(payment, paymentDao.get(1));
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGetAll() {
		paymentDao.getAll();
	}

	@Test
	public void testInsert() {
		assertEquals(1, paymentDao.insert(payment));
	}

	@Test
	public void testUpdate() {
		assertEquals(1, paymentDao.update(payment));
	}

	@Test
	public void testGetPayByUserId() {
		paymentDao.getPayByUserId(payment.getUser());
	}

	@Test
	public void testGetPayByPeriod() {
		paymentDao.getPayByPeriod(payment.getUser(), payment.getDateCreated(),
				payment.getDateEnd());
	}

	@Test
	public void testGetEndedAvailablePays() {
		paymentDao.getEndedAvailablePays();
	}

	@Test
	public void testDisableEndedPayments() {
		paymentDao.disableEndedPayments();
	}

	@Test
	public void testCanDisableUser() {
		paymentDao.canDisableUser(payment.getUser());
	}

	@Test
	public void testGetCurrentPayment() {
		assertEquals(payment, paymentDao.getCurrentPayment(83));
	}

	@Test
	public void testGetAvailableUsersPays() {
		assertEquals(1, paymentDao.getAvailableUserPays(83).size());
	}
	@Test
	public void testGetAvailableEndedPays() {
		assertEquals(0, paymentDao.getAvailableEndedPays().size());
	}
	@Test
	public void testGetLastUserPayment() {
		assertEquals(payment, paymentDao.getLastUserPayment(83));
	}
	// @Test
	// public void testExecuteTransaction(String[] sql, Object[][] args) {
	// paymentDao.executeTransaction(sql, args);
	// }

}
