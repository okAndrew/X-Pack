package main.test.java.com.epam.lab.controller.dao.folder;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Folder;

public class FolderDAOTest {

	private static FolderDAOImpl folderDao = new FolderDAOImpl();
	private static Folder folder = new Folder();
	private static Timestamp dateT = null;

	@BeforeClass
	public static void setFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date;
		try {
			date = sdf.parse("2013-11-07 02:21:23");
			dateT = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		folder.setId(60).setIdUser(83).setName("root").setIdUpper(0)
				.setSize(988533282).setDate(dateT);
	}

	@Test
	public void testGet() {
		assertEquals(folder, folderDao.get(60));
	}

	@Test
	public void testGetRoot() {
		assertEquals(folder, folderDao.getRoot(83));
	}

	@Test
	public void testGetByUpperId() {
		assertEquals(6, folderDao.getByUpperId(60).size());
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGetAll() {
		folderDao.getAll();
	}

	@Test
	public void testInsert() {
		assertEquals(1, folderDao.insert(folder));
	}

	@Test
	public void testUpdate() {
		assertEquals(1, folderDao.update(folder));
	}
}
