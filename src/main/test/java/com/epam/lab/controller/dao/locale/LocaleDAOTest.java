package main.test.java.com.epam.lab.controller.dao.locale;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.language.LanguageDAOImpl;
import com.epam.lab.controller.dao.locale.LocaleDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Language;
import com.epam.lab.model.Locale;

public class LocaleDAOTest {
	private static LocaleDAOImpl localeDao = new LocaleDAOImpl();
	private static Locale locale = new Locale();

	@BeforeClass
	public static void setLocale() {
		locale.setId(6).setLocale("uk_UA").setLanguage("Ukrainian");
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGet() {
		assertEquals(locale, localeDao.get(6));
	}

	@Test
	public void testGetAll() {
		assertEquals(6, localeDao.getAll().size());
	}

	@Test(expected = NoSupportedActionException.class)
	public void testInsert() {
		assertEquals(1, localeDao.insert(locale));
	}

	@Test(expected = NoSupportedActionException.class)
	public void testUpdate() {
		assertEquals(1, localeDao.update(locale));
	}

	@Test
	public void testGetByLocale() {
		assertEquals(locale, localeDao.getByLocale("uk_UA"));
	}
}
