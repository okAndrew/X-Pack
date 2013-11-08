package main.test.java.com.epam.lab.controller.dao.tariff;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.tariff.TariffDAOImpl;
import com.epam.lab.model.Tariff;

public class TariffDaoTest {
	private static TariffDAOImpl tariffDao = new TariffDAOImpl();
	private static Tariff tariff = new Tariff();

	@BeforeClass
	public static void setTariff() {
		tariff.setId(3).setName("Gold").setMaxCapacity(2097152000)
				.setPosition(4).setPrice(4.99).setDescription("Third tariff")
				.setIsDelete(false);
	}

	@Test
	public void testGet() {
		assertEquals(tariff, tariffDao.get(3));
	}

	@Test
	public void testGetTariff() {
		assertEquals(tariff, tariffDao.get(3, "English"));
	}

	@Test
	public void testGetAll() {
		assertEquals(5, tariffDao.getAll().size());
	}

	@Test
	public void testGetAllByLanguage() {
		assertEquals(5, tariffDao.getAll("Ukranian").size());
	}

	@Test
	public void testGetAvailableTariffs() {
		assertEquals(5, tariffDao.getAvailableTariffs().size());
	}

	@Test
	public void testGetAvailableTariffsByLanguage() {
		assertEquals(5, tariffDao.getAvailableTariffs("English").size());
	}

	@Test
	public void testSetIsDelete() {
		assertEquals(1, tariffDao.setIsDelete(false, 3));
	}

	@Test
	public void testGetCount() {
		assertEquals(5, tariffDao.getCount());
	}

	@Test(expected = RuntimeException.class)
	public void testInsert() {
		assertEquals(1, tariffDao.insert(tariff));
	}

	@Test(expected = RuntimeException.class)
	public void testUpdate() {
		assertEquals(1, tariffDao.update(tariff));
	}
}
