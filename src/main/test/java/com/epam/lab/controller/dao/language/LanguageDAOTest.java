package main.test.java.com.epam.lab.controller.dao.language;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.language.LanguageDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Language;

public class LanguageDAOTest {

	private static LanguageDAOImpl languageDao = new LanguageDAOImpl();
	private static Language language = new Language();

	@BeforeClass
	public static void setLanguage() {
		language.setId(1).setName("English").setDefaultLocale("en")
				.setPathImage("res/img/flags/United-States-Flag-icon.png");
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGet() {
		languageDao.get(1);
	}

	@Test
	public void testGetAll() {
		assertEquals(3, languageDao.getAll().size());
	}

	@Test(expected = NoSupportedActionException.class)
	public void testInsert() {
		languageDao.insert(language);
	}

	@Test(expected = NoSupportedActionException.class)
	public void testUpdate() {
		languageDao.update(language);
	}

	@Test(expected = NoSupportedActionException.class)
	public void testDelete() {
		languageDao.delete(1);
	}

	@Test
	public void testGetByLang() {
		assertEquals(language, languageDao.getByLang("English"));
	}
}
