package com.epam.lab.controller.services.file;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import com.epam.lab.controller.dao.impl.FileDAOImpl;

import org.apache.log4j.Logger;

public abstract class FileService {
	private static final Logger logger = Logger.getLogger(FileService.class);
	public static final String ROOT_PATH = "E:/files/";
	public static final int MAX_FILES = 999;

	/*
	 * return hash md5(time+fileName+userName)
	 */
	public static String getFileName(String fileName, String userLogin) {
		String result = Long.toString(Calendar.getInstance().getTimeInMillis())
				+ fileName + userLogin;
		try {
			result = MessageDigest.getInstance("MD5").digest(result.getBytes())
					.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * return current month/year/day String for folder (2013/11/1, 2013/11/2,
	 * 2014/1/13 ...)
	 */
	private static String getCurDatePath() {
		Calendar now = Calendar.getInstance();

		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DATE);

		StringBuilder temp = new StringBuilder().append(year)
				.append(File.separator).append(month).append(File.separator)
				.append(day);

		return temp.toString();
	}

	/*
	 * return current folder to save files
	 */
	public static File getFolder() {
		File curFolder = null;
		File path = new File(ROOT_PATH + getCurDatePath());

		if (!path.exists()) {
			path.mkdirs();
		}

		File[] folders = path.listFiles();

		int curFolderName = 0;
		for (int i = folders.length; i > 0; i--) {
			if (folders[i - 1].isDirectory()) {
				// if last folder is not valid
				try {
					curFolderName = Integer.valueOf(folders[i - 1].getName());
				} catch (NumberFormatException e) {
					continue;
				}

				// if last folder is full else get current last valid folder
				if (folders[i - 1].listFiles().length >= MAX_FILES) {
					curFolder = newIncfolder(curFolderName);
				} else {
					curFolder = folders[i - 1];
				}

				break;
			}
		}

		// if month folder is empty
		if (curFolder == null) {
			curFolder = new File(path + File.separator + 1);
			curFolder.mkdir();
		}

		return curFolder;
	}

	// create new increment valid folder
	private static File newIncfolder(int cur) {
		File newFolder = null;
		StringBuilder path = new StringBuilder().append(ROOT_PATH)
				.append(getCurDatePath()).append(File.separator);

		while (newFolder == null) {
			File folder = new File(path.toString() + cur);

			// if folder !exist
			if (folder.mkdir()) {
				newFolder = folder;
			} else {
				cur++;
			}
		}

		return newFolder;
	}

	public static List<com.epam.lab.model.File> getAllFiles(long iduser) {
		List<com.epam.lab.model.File> files = null;
		FileDAOImpl filedaoimpl = new FileDAOImpl();
		files = filedaoimpl.getAllbyUserId(iduser);
		return files;
	}

	public static void deleteFiles(long id) {
		FileDAOImpl filedaoimp = new FileDAOImpl();
		filedaoimp.delete(id);
	}

	public static List<com.epam.lab.model.File> getFiles(long userid,
			long folderId) {
		List<com.epam.lab.model.File> files = null;
		FileDAOImpl filedaoimpl = new FileDAOImpl();
		files = filedaoimpl.getAllbyUserIdAndFolderId(userid, folderId);
		return files;
	}
}
