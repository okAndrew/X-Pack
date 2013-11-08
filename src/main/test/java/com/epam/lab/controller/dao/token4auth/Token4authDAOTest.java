package main.test.java.com.epam.lab.controller.dao.token4auth;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.dao.token4auth.Token4AuthDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Token;
import com.epam.lab.model.Token4Auth;

public class Token4authDAOTest {
	private static Token4AuthDAOImpl token4AuthDao = new Token4AuthDAOImpl();
	private static Token4Auth token4Auth = new Token4Auth();
	private static Timestamp dateT = null;

	@BeforeClass
	public static void setToken4Auth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date;
		try {
			date = sdf.parse("2013-11-07 01:19:51");
			dateT = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		token4Auth.setId(1);
		token4Auth.setDestroyDate(dateT);
		token4Auth.setIdUser(1);
		token4Auth.setToken("c1fb8205cbe144dcd4c0912734553498");
	}

	@Test
	public void testGet() {
		assertEquals(token4Auth, token4AuthDao.get(1));
	}

	@Test
	public void testGetByToken() {
		assertEquals(token4Auth,
				token4AuthDao.getByToken("c1fb8205cbe144dcd4c0912734553498"));
	}

	@Test
	public void testGetAll() {
		assertEquals(1, token4AuthDao.getAll().size());
		;
	}

	@Test(expected = RuntimeException.class)
	public void testInsert() {
		assertEquals(1, token4AuthDao.insert(token4Auth));
	}

	@Test(expected = RuntimeException.class)
	public void testUpdate() {
		assertEquals(1, token4AuthDao.update(token4Auth));
	}

}
