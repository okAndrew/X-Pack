package main.test.java.com.epam.lab.controller.dao.user;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.user.UserDAOImpl;
import com.epam.lab.model.User;

public class UserDAOTest {
	private static UserDAOImpl userDAOImpl = new UserDAOImpl();
	private static User user = new User();
	private static User userSecond = new User();

	@BeforeClass
	public static void setTrafficHistory() {
		user.setId(92).setCapacity(18471550).setEmail("CristianGatu@utiril.rm")
				.setIdTariff(3).setPassword("1d835b180dbabffbd7db871ca0e5f3cf")
				.setLogin("CristianGatu").setIsActivated(true)
				.setIsBanned(false).setLastLanguage(String.valueOf(2))
				.setRole(1);
		userSecond.setId(92).setCapacity(18471550)
				.setEmail("CristianGatu@utiril.rm").setIdTariff(3)
				.setPassword("1d835b180dbabffbd7db871ca0e5f3cf")
				.setLogin("CristianGatu").setIsActivated(true)
				.setIsBanned(false).setLastLanguage("uk").setRole(1);
	}

	@Test
	public void testGet() {
		assertEquals(userSecond, userDAOImpl.get(92));
	}

	@Test
	public void testGetAll() {
		assertEquals(12, userDAOImpl.getAll().size());
	}

	@Test
	public void testCount() {
		assertEquals(12, userDAOImpl.getCount());
	}

	@Test
	public void testBySQL() {
		assertEquals(1,
				userDAOImpl.getBySQL("Select * from users where id = 92;")
						.size());
	}

	@Test(expected = RuntimeException.class)
	public void testInsert() {
		assertEquals(1, userDAOImpl.insert(user));
	}

	@Test
	public void testUpdate() {
		assertEquals(1, userDAOImpl.update(user));
	}

	@Test
	public void testGetByEmail() {
		assertEquals(user, userDAOImpl.getByEmail("CristianGatu@utiril.rm"));
	}

	@Test
	public void testGetByLogin() {
		assertEquals(user, userDAOImpl.getByLogin("CristianGatu"));
	}

	@Test
	public void testCheckEmailById() {
		assertEquals(true,
				userDAOImpl.checkEmailById("CristianGatu@utiril.rm", 92));
	}

	@Test
	public void testSetIsActivate() {
		assertEquals(1, userDAOImpl.setIsActivate(true, 92));
	}

	@Test
	public void testSetIsBanned() {
		assertEquals(1, userDAOImpl.setIsBanned(false, 92));
	}

	@Test
	public void testGetBannedUsers() {
		assertEquals(2, userDAOImpl.getBannedUsers().size());
	}

	@Test
	public void testSetLastLocale() {
		assertEquals(1, userDAOImpl.setLastLocale(2, 92));
	}
}
