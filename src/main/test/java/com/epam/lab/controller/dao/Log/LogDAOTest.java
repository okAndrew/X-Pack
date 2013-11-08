package main.test.java.com.epam.lab.controller.dao.Log;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.logger.LogDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Log;

public class LogDAOTest {
	private static LogDAOImpl logDao = new LogDAOImpl();
	private static Log log = new Log();
	private static Timestamp dateT = null;

	@BeforeClass
	public static void setLocale() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date;
		try {
			date = sdf.parse("2013-11-07 00:45:08");
			dateT = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		log.setId(111);
		log.setDatetime(dateT);
		log.setLogger("com.epam.lab.controller.web.listeners.AppScheduler$setUsersForFree");
		log.setLevel("DEBUG");
		log.setMessage("setUsersForFree");
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGet() {
		assertEquals(log, logDao.get(111));
	}

	@Test
	public void testGetAll() {
		assertEquals(400, logDao.getAll().size());
	}

	@Test(expected = NoSupportedActionException.class)
	public void testInsert() {
		assertEquals(1, logDao.insert(log));
	}

	@Test(expected = NoSupportedActionException.class)
	public void testUpdate() {
		assertEquals(1, logDao.update(log));
	}

	@Test
	public void testGetCount() {
		assertEquals(385, logDao.getCount());
	}
}
