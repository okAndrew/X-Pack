package main.test.java.com.epam.lab.controller.dao.sessionhistory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.sessionhistory.SessionHistoryDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryDAOTest {

	private static SessionHistory session = new SessionHistory();
	private static SessionHistoryDAOImpl sessionHistoryDao = new SessionHistoryDAOImpl();
	private static Timestamp startDate = null;
	private static Timestamp endDate = null;

	@BeforeClass
	public static void setSessionHistory() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date sDate, eDate;
		try {
			sDate = sdf.parse("2013-11-07 02:17:11");
			eDate = sdf.parse("2013-11-07 02:20:15");
			startDate = new Timestamp(sDate.getTime());
			endDate = new Timestamp(eDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		session.setId(1077).setUserid((long) 83).setStartdate(startDate)
				.setEnddate(endDate)
				.setSessIdTomcat("DCAA8E986E01A4E0B72433E862779C20");
	}

	@AfterClass
	public static void clearObject() {
		startDate = null;
		endDate = null;
	}

	@Test
	public void testGet() {
		assertEquals(session, sessionHistoryDao.get(1077));
	}

	@Test
	public void testGetAll() {
		assertNotNull(sessionHistoryDao.getAll());
	}

	@Test
	public void testInsert() {
		assertEquals(1, sessionHistoryDao.insert(session));
	}

	@Test
	public void testInsertWithoutUser() {
		assertEquals(1, sessionHistoryDao.insertWithoutUser(session));
	}

	@Test
	public void testUpdate() {
		assertEquals(1, sessionHistoryDao.update(session));
	}

	@Test
	public void testSetUserId() {
		assertEquals(1, sessionHistoryDao.setUserId(session));
	}

	@Test(expected = NoSupportedActionException.class)
	public void testDelete() {
		sessionHistoryDao.delete(1077);
	}

	@Test
	public void testGetSessionHistBySessIdTomcat() {
		assertNotNull(sessionHistoryDao
				.getSessionHistBySessIdTomcat("DCAA8E986E01A4E0B72433E862779C20"));
	}

	@Test
	public void testGetLoggedVisitorsByDate() {
		assertNotNull(sessionHistoryDao.getLoggedVisitorsByDate(startDate,
				endDate));
	}

	@Test
	public void testGetAllVisitorsByDate() {
		assertNotNull(sessionHistoryDao
				.getAllVisitorsByDate(startDate, endDate));
	}
}
