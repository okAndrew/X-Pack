package main.test.java.com.epam.lab.controller.dao.file;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.FileType;
import com.epam.lab.model.UserFile;

public class FIleDAOTest {

	private static UserFile file = new UserFile();
	private static UserFile fileSizeForDates = new UserFile();
	private static FileDAOImpl fileDao = new FileDAOImpl();
	private static Timestamp dateT = null;

	@BeforeClass
	public static void setUserFile() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date;
		try {
			date = sdf.parse("2013-11-07 02:21:23");
			dateT = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		file.setId((long) 3).setDate(dateT).setIdFolder((long) 70)
				.setNameIncome("tasks.2-16.JavaST.Strings.doc")
				.setName("a5e5c73510eb927abeb27fa371002f8d.doc")
				.setPath("C:\\files\\2013\\10\\7\\1")
				.setType(FileType.OTHERTYPE).setSize(31232).setIdUser(83)
				.setIsPublic(true);
		fileSizeForDates.setSize(85504);
	}

	@Test
	public void testGet() {
		assertEquals(file, fileDao.get(3));
	}

	@Test
	public void testGetByName() {
		assertEquals(file,
				fileDao.getByName("a5e5c73510eb927abeb27fa371002f8d.doc"));
	}

	@Test
	public void testGetSizeUploadByDates() {
		assertEquals(fileSizeForDates,
				fileDao.getSizeUploadByDates(dateT, dateT));
	}

	@Test
	public void testGetSizeUploadUserByDates() {
		assertEquals(fileSizeForDates,
				fileDao.getSizeUploadUserByDates(dateT, dateT, 83));
	}

	@Test(expected = NoSupportedActionException.class)
	public void testGetAll() {
		fileDao.getAll();
	}

	@Test
	public void testGetAllByUserId() {
		assertEquals(15, fileDao.getAllByUserId(83).size());
	}

	@Test
	public void testGetAllByFolderId() {
		assertEquals(3, fileDao.getAllByFolderId(70).size());
	}

	@Test
	public void testGetFilesGroupType() {
		assertEquals(5, fileDao.getFilesGroupType().size());
	}

	@Test(expected = RuntimeException.class)
	public void testInsert() {
		assertEquals(1, fileDao.insert(file));
	}

	@Test
	public void testUpdate() {
		assertEquals(1, fileDao.update(file));
	}

	@Test
	public void testUpdateSize() {
		assertEquals(1, fileDao.updateSize(3, 31232));
	}

}
