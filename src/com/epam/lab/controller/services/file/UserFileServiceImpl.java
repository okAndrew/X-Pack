package com.epam.lab.controller.services.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.FileType;
import com.epam.lab.model.FilesTypesSize;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

public class UserFileServiceImpl extends AbstractServiceImpl<UserFile>
		implements UserFileService {
	private FileDAOImpl fileDAOImpl = (FileDAOImpl) dao;
	private static final Logger logger = Logger
			.getLogger(UserFileServiceImpl.class);
	private static long count;
	private static final Properties PROP;
	private static final int MAX_FILES;
	private static final String ROOT_PATH;
	private static final String HOST;
	static {
		PROP = new Properties();
		MAX_FILES = 1000;
		try {
			InputStream is = UserFileServiceImpl.class
					.getResourceAsStream("path.properties");
			PROP.load(is);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
		ROOT_PATH = PROP.getProperty("rootPath");
		HOST = PROP.getProperty("host");
	}

	public UserFileServiceImpl() {
		super(new FileDAOImpl());
	}

	public UserFile createFileInfo(String nameIncome, Long idFolder,
			Long idUser, Boolean isPublic, Long size) {
		UserFile userFile = new UserFile();
		if (nameIncome == null) {
			nameIncome = new MD5Encrypter().generateRandomHash();
		}
		userFile.setNameIncome(nameIncome);
		userFile.setIdFolder(idFolder);
		userFile.setIdUser(idUser);
		userFile.setIsPublic(isPublic);
		userFile.setDate(TimeStampManager.getCurrentTime());
		userFile.setSize(size);
		userFile.setPath(this.getFolder().getAbsolutePath());
		String extention = FileType.getExtention(nameIncome);
		FileType fileType = FileType.findByExtention(extention);
		userFile.setType(fileType);
		do {
			// generate, while name will not be unique
			userFile.setName(new MD5Encrypter().generateRandomHash()
					+ extention);
		} while (fileDAOImpl.insert(userFile) == 0);
		return fileDAOImpl.getByName(userFile.getName());
	}

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
		files = fileDAOImpl.getAllByUserId(userId);
		return files;
	}

	public List<UserFile> getByFolderId(long folderId) {
		List<UserFile> files = null;
		files = fileDAOImpl.getAllByFolderId(folderId);
		Collections.sort(files);
		return files;
	}

	public int delete(long id) {
		UserFile f = fileDAOImpl.get(id);
		if (f != null) {
			File file = new File(f.getPath() + File.separator + f.getName());
			file.delete();
			FolderServiceImpl service = new FolderServiceImpl();
			service.updateSize(f.getIdFolder(), -f.getSize());
			return fileDAOImpl.delete(id);
		}
		return 0;
	}

	public File getArchive(String[] filesIds, String[] foldersIds) {
		return new File(getArchivePath(filesIds, foldersIds));
	}

	public boolean check(long folderId, long fileId, String name) {
		String fullNewNameIncome = name + getExtention(fileId);
		List<UserFile> files = fileDAOImpl.getAllByFolderId(folderId);
		for (UserFile file : files) {
			if (file.getNameIncome().equals(fullNewNameIncome)) {
				return true;
			}
		}
		return false;
	}

	public void moveFile(long fileIdMove, long folderIdTarget) {
		UserFile file = fileDAOImpl.get(fileIdMove);
		FolderServiceImpl service = new FolderServiceImpl();
		service.updateSize(file.getIdFolder(), -file.getSize());
		file.setIdFolder(folderIdTarget);
		service.updateSize(folderIdTarget, file.getSize());
		fileDAOImpl.update(file);
	}

	public UserFile rename(long fileId, String newNameIncome) {
		UserFile file = fileDAOImpl.get(fileId);
		String extention = getExtention(fileId);
		file.setNameIncome(newNameIncome + extention);
		fileDAOImpl.update(file);
		return fileDAOImpl.get(file.getId());
	}

	@Override
	public int deleteByUserId(long userId) {
		int result = fileDAOImpl.deleteByUserId(userId);
		return result;
	}

	@Override
	public long getUploadTrafficByDates(Timestamp dataStart, Timestamp dataEnd) {
		UserFile file = new UserFile();
		file = fileDAOImpl.getSizeUploadByDates(dataStart, dataEnd);
		return file.getSize();
	}

	public boolean isUsersFile(long id, long userId) {
		UserFile file = fileDAOImpl.get(id);
		if (file.getIdUser() == userId) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public long getUploadTrafficUserByDates(Timestamp dataStart,
			Timestamp dataEnd, long userId) {
		UserFile file = new UserFile();
		file = fileDAOImpl.getSizeUploadUserByDates(dataStart, dataEnd, userId);
		return file.getSize();
	}

	public UserFile getByName(String fileName) {
		return fileDAOImpl.getByName(fileName);
	}

	@Override
	public void changePublicState(long id, boolean state) {
		UserFile file = fileDAOImpl.get(id);
		file.setIsPublic(state);
		fileDAOImpl.update(file);
	}

	@Override
	public String getLink(long fileId) {
		String name = fileDAOImpl.get(fileId).getName();
		return HOST + "download?file=" + name;
	}

	public List<FilesTypesSize> getTypesFiles() {
		return fileDAOImpl.getFilesGroupType();
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
			if (foldersIds != null) {
				for (int i = 0; i < foldersIds.length; i++) {
					FolderDAOImpl dao = new FolderDAOImpl();
					Folder fold = dao.get(Long.parseLong(foldersIds[i]));
					addToArchive(fold, out);
				}
			}
			if (filesIds != null) {
				for (int i = 0; i < filesIds.length; i++) {
					UserFile file = fileDAOImpl
							.get(Long.parseLong(filesIds[i]));
					addToArchive(file, out);
				}
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
		List<UserFile> files = fileDAOImpl.getAllByFolderId(folder.getId());
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

	private String getExtention(long fileId) {
		UserFile file = fileDAOImpl.get(fileId);
		int lastPointIndex = file.getNameIncome().lastIndexOf(".");
		String extention = file.getNameIncome().substring(lastPointIndex);
		return extention;
	}

	public boolean editFileOrFolder(String name, long fileId, long upperId,
			long folderId, long userId) {
		boolean result = false;
		FolderServiceImpl service = new FolderServiceImpl();
		if ((fileId == 0) && !service.check(name, userId, upperId)
				&& Validator.FILE_NAME.validate(name)) {
			Folder folder = service.get(folderId);
			folder.setName(name);
			service.update(folder);
		} else {
			result = true;
		}
		if ((folderId == 0) && Validator.FILE_NAME.validate(name)) {
			rename(fileId, name);
			result = false;
		}
		return result;
	}

	@Override
	public void refresh(long folderId) {
		List<UserFile> files = fileDAOImpl.getAllByFolderId(folderId);
		for (UserFile file : files) {
			File f = new File(file.getPath() + File.separator + file.getName());
			if (!f.exists()) {
				fileDAOImpl.delete(file.getId());
			}
		}
	}

	@Override
	public void cleanTempDirectory() {
		File temp = new File(ROOT_PATH + "/tmp");
		if (temp.isDirectory()) {
			try {
				FileUtils.cleanDirectory(temp);
			} catch (IOException e) {
				logger.warn(e);
			}
		} else {
			temp.mkdirs();
		}
	}

}
