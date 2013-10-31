package com.epam.lab.controller.services.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.file.FileDAO;
import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.FileType;
import com.epam.lab.model.Folder;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public class UserFileUploader {
	private static Logger logger = Logger.getLogger(UserFileUploader.class);

	public UserFile uploadFile(FileItem fileItem, String fileIncomeName,
			long idFolder) throws FolderNotFoundException, IOException,
			FileTooLargeException {
		UserFile userFile = null;
		userFile = createUserFile(fileIncomeName, idFolder);
		String uploadedFileLocation = getUploadFileLocation(userFile);
		// verify capacity
		User user = new UserServiceImpl().getUserByFolderId(idFolder);
		long maxCapacity = getMaxCapacity(user);
		if (user.getCapacity() + fileItem.getSize() > maxCapacity) {
			throw new FileTooLargeException();
		}
		try {
			fileItem.write(new File(uploadedFileLocation));
		} catch (Exception e) {
			logger.error(e);
			throw new IOException();
		}
		userFile.setSize(fileItem.getSize());

		userFile = writeUploadActionIntoDB(userFile);
		return userFile;
	}

	private long getMaxCapacity(User user) {
		Tariff tariff = new TariffServiseImpl().get(user.getIdTariff());
		return tariff.getMaxCapacity();
	}

	public UserFile uploadFile(InputStream inputStream, String fileIncomeName,
			long idFolder) throws FolderNotFoundException, IOException,
			FileTooLargeException {
		UserFile userFile = null;
		userFile = createUserFile(fileIncomeName, idFolder);
		String uploadedFileLocation = getUploadFileLocation(userFile);

		// verify capacity (when file will be download)
		User user = new UserServiceImpl().getUserByFolderId(idFolder);
		long maxFileSize = getMaxCapacity(user) - user.getCapacity();
		File uploadedFile = writeFile(inputStream, uploadedFileLocation,
				maxFileSize);

		userFile.setSize(uploadedFile.length());
		userFile = writeUploadActionIntoDB(userFile);
		return userFile;
	}

	public List<UserFile> uploadFiles(List<FileItem> items, long idFolder)
			throws FolderNotFoundException, FileTooLargeException {
		List<UserFile> files = new ArrayList<UserFile>();
		for (FileItem item : items) {
			UserFile file = null;
			try {
				file = uploadFile(item, item.getName(), idFolder);
			} catch (IOException e) {
				logger.error(e);
			}
			files.add(file);
		}
		return files;

	}

	private File writeFile(InputStream inputStream,
			String uploadedFileLocation, long maxSize) throws IOException,
			FileTooLargeException {
		File newFile = new File(uploadedFileLocation);
		OutputStream out = new FileOutputStream(newFile);
		long sumWritenBytes = 0;
		int read = 0;
		byte[] bytes = new byte[1024];
		try {
			while ((read = inputStream.read(bytes)) != -1) {
				if (sumWritenBytes > maxSize) {
					throw new FileTooLargeException();
				}
				out.write(bytes, 0, read);
			}
		} catch (IOException e) {
			logger.error(e);
		} finally {
			newFile.delete();
			out.flush();
			out.close();
		}
		return newFile;
	}

	private String getUploadFileLocation(UserFile userFile) {
		return userFile.getPath() + File.separator + userFile.getName();
	}

	private void updateFolders(UserFile file) {
		FolderService service = new FolderServiceImpl();
		service.updateSize(file.getIdFolder(), file.getSize());
	}

	private UserFile writeUploadActionIntoDB(UserFile userFile) {
		FileDAO dao = new FileDAOImpl();
		updateFolders(userFile);
		dao.insert(userFile);
		return dao.getByName(userFile.getName());
	}

	private UserFile createUserFile(String fileNameIncome, long idFolder)
			throws FolderNotFoundException {
		UserFile userFile = createUserFileWithName(fileNameIncome);
		setUserFileInfo(userFile, idFolder);
		return userFile;
	}

	private UserFile createUserFileWithName(String fileNameIncome) {
		UserFile userFile = new UserFile();
		if (fileNameIncome != null) {
			String extention = FileType.getExtention(fileNameIncome);
			userFile.setType(FileType.findByExtention(extention));
			userFile.setNameIncome(fileNameIncome);
		}
		return userFile;
	}

	private void setUserFileInfo(UserFile userFile, long idFolder)
			throws FolderNotFoundException {
		Folder folder = new FolderDAOImpl().get(idFolder);
		if (folder == null)
			throw new FolderNotFoundException();
		User user = new UserServiceImpl().get(folder.getIdUser());
		Timestamp currentTS = TimeStampManager.getFormatCurrentTimeStamp();

		String fileName = new MD5Encrypter().encrypt(currentTS.toString()
				+ userFile.getNameIncome() + new Random().nextLong());

		String filePath = new UserFileServiceImpl().getFolder()
				.getAbsolutePath();
		userFile.setName(fileName).setPath(filePath).setDate(currentTS)
				.setIdFolder(folder.getId()).setIdUser(user.getId());
	}
}