package main.test.java.com.epam.lab.controller.dao.file;

import com.epam.lab.controller.dao.locale.LocaleDAOImpl;
import com.epam.lab.controller.dao.logger.LogDAOImpl;
import com.epam.lab.controller.dao.payment.PaymentDAOImpl;
import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.model.Payment;
import com.epam.lab.model.Tariff;

public class Test {
	private static PaymentDAOImpl paymentDao = new PaymentDAOImpl();
	private static LogDAOImpl logDao = new LogDAOImpl();
	private static LocaleDAOImpl localeDao = new LocaleDAOImpl();
	private static TariffDAOImpl tariffDao = new TariffDAOImpl();

	public static void main(String[] args) {
		Payment payment = new Payment();
		Tariff tariff =new Tariff();
		tariff = tariffDao.get(3, "English");
		System.out.println(tariffDao.getByParam(1, 2, "position ASC", "2", "English"));
}
}
