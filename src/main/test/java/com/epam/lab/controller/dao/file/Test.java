package main.test.java.com.epam.lab.controller.dao.file;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;

import com.epam.lab.controller.dao.locale.LocaleDAOImpl;
import com.epam.lab.controller.dao.logger.LogDAOImpl;
import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.controller.dao.traffichistory.TrafficHistoryDAOImpl;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Tariff;

public class Test {
	private static PaymentDAOImpl paymentDao = new PaymentDAOImpl();
	private static LogDAOImpl logDao = new LogDAOImpl();
	private static LocaleDAOImpl localeDao = new LocaleDAOImpl();
	private static TariffDAOImpl tariffDao = new TariffDAOImpl();
	private static TrafficHistoryDAOImpl trafficHistoryDAOImpl = new TrafficHistoryDAOImpl();
	private static Timestamp dateT = null;
	private static Timestamp dateENd = null;
		

	public static void main(String[] args) throws ParseException {
		Payment payment = new Payment();
		Tariff tariff =new Tariff();
		tariff = tariffDao.get(3, "English");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date, date2;
			date = sdf.parse("2013-11-07 02:31:01");
			date2 = sdf.parse("2013-13-07 02:31:01");
			dateT = new Timestamp(date.getTime());
			dateENd = new Timestamp(date.getTime());
		System.out.println(trafficHistoryDAOImpl.getDownloadTrafficUserByDates(dateT, dateENd, 83).toString());
}
}
