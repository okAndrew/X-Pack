package com.epam.lab.controller.services.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.controller.services.folder.FolderService;

import org.apache.log4j.Logger;

public class FileService {
	private static final Logger logger = Logger.getLogger(FileService.class);
	private static long count;
	public static final String ROOT_PATH = "E:/files/";
	public static final int MAX_FILES = 999;

	/*
	 * return current folder to save files
	 */
	public File getFolder() {
		FileService service = new FileService();
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
					curFolder = service.newIncfolder(curFolderName);
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

	public com.epam.lab.model.File get(long id) {
		FileDAOImpl dao = new FileDAOImpl();
		com.epam.lab.model.File file = dao.get(id);
		return file;
	}

	public List<com.epam.lab.model.File> getByUserId(long userId) {
		List<com.epam.lab.model.File> files = null;
		FileDAOImpl dao = new FileDAOImpl();
		files = dao.getAllByUserId(userId);
		return files;
	}

	public List<com.epam.lab.model.File> getByFolderId(long folderId) {
		List<com.epam.lab.model.File> files = null;
		FileDAOImpl dao = new FileDAOImpl();
		files = dao.getAllByFolderId(folderId);
		return files;
	}

	public void delete(long id) {
		FileDAOImpl dao = new FileDAOImpl();
		com.epam.lab.model.File f = dao.get(id);
		if (f != null) {
			File file = new File(f.getPath() + File.separator + f.getName());
			file.delete();
			FolderService service = new FolderService();
			service.updateSize(f.getIdFolder(), -f.getSize());
			dao.delete(id);
		}
	}

	public String getArchivePath(String[] ids) {
		StringBuilder zipPath = new StringBuilder();
		zipPath.append(ROOT_PATH).append("tmp").append(File.separator);
		File folder = new File(zipPath.toString());
		if (!folder.exists()) {
			folder.mkdirs();
		}
		zipPath.append(++count).append(".zip");
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipPath.toString()));
			for (int i = 0; i < ids.length; i++) {
				long id = Long.parseLong(ids[i]);
				FileDAOImpl dao = new FileDAOImpl();
				com.epam.lab.model.File f = dao.get(id);
				String path = f.getPath() + File.separator + f.getName();
				FileInputStream in = new FileInputStream(path);
				out.putNextEntry(new ZipEntry(f.getNameIncome()));
				byte[] b = new byte[1024];
				int count;
				while ((count = in.read(b)) > 0) {
					out.write(b, 0, count);
				}
				in.close();
			}
			out.close();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return zipPath.toString();
	}

	public List<com.epam.lab.model.File> getSearchedFiles(long userId,
			String text) {
		FileService service = new FileService();
		List<com.epam.lab.model.File> files = service.getByUserId(userId);
		List<com.epam.lab.model.File> result = new ArrayList<com.epam.lab.model.File>();
		for (com.epam.lab.model.File file : files) {
			if (file.getNameIncome().contains(text)) {
				result.add(file);
			}
		}
		return result;
	}

	/*
	 * return current month/year/day String for folder (2013/11/1, 2013/11/2,
	 * 2014/1/13 ...)
	 */
	private String getCurDatePath() {
		Calendar now = Calendar.getInstance();

		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DATE);

		StringBuilder temp = new StringBuilder().append(year)
				.append(File.separator).append(month).append(File.separator)
				.append(day);

		return temp.toString();
	}

	// create new increment valid folder
	private File newIncfolder(int cur) {
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
}