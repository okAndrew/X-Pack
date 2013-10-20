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

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

import org.apache.log4j.Logger;

public class UserFileServiceImpl extends AbstractServiceImpl<UserFile>
		implements UserFileService {
	private static final Logger logger = Logger
			.getLogger(UserFileServiceImpl.class);
	private static long count;
	public static final String ROOT_PATH = "E:/files/";
	public static final int MAX_FILES = 999;

	/*
	 * return current folder to save files
	 */
	public File getFolder() {
		UserFileServiceImpl service = new UserFileServiceImpl();
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

	public List<UserFile> getByUserId(long userId) {
		List<UserFile> files = null;
		FileDAOImpl dao = new FileDAOImpl();
		files = dao.getAllByUserId(userId);
		return files;
	}

	public List<UserFile> getByFolderId(long folderId) {
		List<UserFile> files = null;
		FileDAOImpl dao = new FileDAOImpl();
		files = dao.getAllByFolderId(folderId);
		return files;
	}

	public int delete(long id) {
		FileDAOImpl dao = new FileDAOImpl();
		UserFile f = dao.get(id);
		if (f != null) {
			File file = new File(f.getPath() + File.separator + f.getName());
			file.delete();
			FolderServiceImpl service = new FolderServiceImpl();
			service.updateSize(f.getIdFolder(), -f.getSize());
			return dao.delete(id);
		}
		return 0;
	}

	public File getArchive(String[] filesIds, String[] foldersIds) {
		return new File(getArchivePath(filesIds, foldersIds));
	}

	public List<UserFile> getSearchedFiles(long userId, String text) {
		UserFileServiceImpl service = new UserFileServiceImpl();
		List<UserFile> files = service.getByUserId(userId);
		List<UserFile> result = new ArrayList<UserFile>();
		for (UserFile file : files) {
			if (file.getNameIncome().contains(text)) {
				result.add(file);
			}
		}
		return result;
	}

	public boolean check(long folderId, String name) {
		FileDAOImpl dao = new FileDAOImpl();
		List<UserFile> files = dao.getAllByFolderId(folderId);
		for (UserFile file : files) {
			if (file.getNameIncome().equals(name)) {
				return true;
			}
		}
		return false;
	}

	// IT'S AWFUL!! 
//	public long update(UserFile file) {
//		FileDAOImpl dao = new FileDAOImpl();
//		dao.update(file);
//		return dao.get(file.getId()).getId();
//	}

	public void movefile(long fileidmove, long folderidtarget) {
		FileDAOImpl dao = new FileDAOImpl();
		UserFile file = dao.get(fileidmove);
		FolderServiceImpl service = new FolderServiceImpl();
		service.updateSize(file.getIdFolder(), -file.getSize());
		file.setIdFolder(folderidtarget);
		service.updateSize(folderidtarget, file.getSize());
		dao.update(file);
	}

	public UserFile rename(long fileId, String newNameIncome) {
		FileDAOImpl dao = new FileDAOImpl();
		UserFile file = dao.get(fileId);
		int lastPointIndex = file.getNameIncome().lastIndexOf(".");
		String extention = file.getNameIncome().substring(lastPointIndex);
		file.setNameIncome(newNameIncome + extention);
		dao.update(file);
		return dao.get(file.getId());
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

	private String getArchivePath(String[] filesIds, String[] foldersIds) {
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
			for (int i = 0; i < foldersIds.length; i++) {
				FolderDAOImpl dao = new FolderDAOImpl();
				Folder fold = dao.get(Long.parseLong(foldersIds[i]));
				addToArchive(fold, out);
			}
			for (int i = 0; i < filesIds.length; i++) {
				FileDAOImpl dao = new FileDAOImpl();
				UserFile file = dao.get(Long.parseLong(filesIds[i]));
				addToArchive(file, out);
			}
			out.close();
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return zipPath.toString();
	}

	private void addToArchive(UserFile file, ZipOutputStream out)
			throws IOException {
		addFileToArchive("", file, out);
	}

	private void addToArchive(Folder folder, ZipOutputStream out)
			throws IOException {
		FileDAOImpl fileDao = new FileDAOImpl();
		List<UserFile> files = fileDao.getAllByFolderId(folder.getId());
		for (UserFile file : files) {
			addFileToArchive(folder.getName() + File.separator, file, out);
		}
	}

	private void addFileToArchive(String path, UserFile file,
			ZipOutputStream out) throws IOException {
		String filePath = file.getPath() + File.separator + file.getName();
		FileInputStream in = new FileInputStream(filePath);
		out.putNextEntry(new ZipEntry(path + file.getNameIncome()));
		byte[] b = new byte[1024];
		int count;
		while ((count = in.read(b)) > 0) {
			out.write(b, 0, count);
		}
		in.close();
	}

}
