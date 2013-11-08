package main.test.java.com.epam.lab.controller.dao.token;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.token.TokenDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Token;

public class TokenDAOTest {
	private static TokenDAOImpl tokenDao = new TokenDAOImpl();
	private static Token token = new Token();
	private static Timestamp dateT = null;

	@BeforeClass
	public static void setToken() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date;
		try {
			date = sdf.parse("2013-11-07 01:19:51");
			dateT = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		token.setId(11);
		token.setDate(dateT);
		token.setIdUser(87);
		token.setToken("c1fb8205cbe144dcd4c0912734553498");
	}

	@Test
	public void testGet() {
		assertEquals(token, tokenDao.get(11));
	}
	@Test
	public void testGetByToken() {
		assertEquals(token, tokenDao.get("c1fb8205cbe144dcd4c0912734553498"));
	}
	@Test(expected = NoSupportedActionException.class)
	public void testGetAll() {
		tokenDao.getAll();
	}

	@Test(expected = RuntimeException.class)
	public void testInsert() {
		assertEquals(1, tokenDao.insert(token));
	}

	@Test(expected = RuntimeException.class)
	public void testUpdate() {
		assertEquals(1, tokenDao.update(token));
	}


}
