package main.test.java.com.epam.lab.controller.dao.traffichistory;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.traffichistory.TrafficHistoryDAOImpl;
import com.epam.lab.model.TrafficHistory;

public class TrafficHistoryDAOTest {
	private static TrafficHistoryDAOImpl trafficHistoryDAOImpl = new TrafficHistoryDAOImpl();
	private static TrafficHistory trafficHistory = new TrafficHistory();
	private static Timestamp dateStart = null;
	private static Timestamp dateEnd = null;
	@BeforeClass
	public static void setTrafficHistory() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date,date2;
		try {
			date = sdf.parse("2013-11-07 02:31:01");
			date2 = sdf.parse("2013-13-07 02:31:01");
			dateStart = new Timestamp(date.getTime());
			dateEnd = new Timestamp(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		trafficHistory.setId(5).setSize(777835).setUserId((long) 83)
				.setDate(dateStart);
	}

	@Test
	public void testGet() {
		assertEquals(trafficHistory, trafficHistoryDAOImpl.get(5));
	}

	@Test
	public void testGetAll() {
		assertEquals(49, trafficHistoryDAOImpl.getAll().size());
	}

	@Test
	public void testInsert() {
		assertEquals(1, trafficHistoryDAOImpl.insert(trafficHistory));
	}

	@Test(expected = RuntimeException.class)
	public void testUpdate() {
		assertEquals(1, trafficHistoryDAOImpl.update(trafficHistory));
	}

	@Test
	public void testGetDownloadTrafficByDates() {
		assertEquals(107220608, trafficHistoryDAOImpl.getDownloadTrafficByDates(dateStart, dateEnd).getSize());
	}
	@Test
	public void testGetDownloadTrafficUserByDates() {
		assertEquals(777835, trafficHistoryDAOImpl.getDownloadTrafficUserByDates(dateStart, dateEnd, 83).getSize());
	}
}
