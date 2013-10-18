package com.epam.lab.controller.services.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.controller.dao.impl.FolderDAOImpl;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class FileUploader {
	private static Logger logger = Logger.getLogger(FileUploader.class);
	private Folder folder = null;

	public FileUploader(long idFolder) {
		this.folder = new FolderDAOImpl().get(idFolder);
	}

	public UserFile uploadFile(FileItem fileItem) {
		FileDAOImpl dao = new FileDAOImpl();
		UserFile userFile = null;
		if (fileItem.getSize() > 0) {
			userFile = createUserFile(fileItem);
			File newFile = new File(getUploadedFileLocation(userFile));
			try {
				fileItem.write(newFile);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
			updateFolders(userFile);
			dao.insert(userFile);
		}
		return userFile;
	}

	public UserFile uploadFile(InputStream inputStream,
			FormDataContentDisposition cd) {
		FileDAOImpl dao = new FileDAOImpl();
		UserFile userFile = null;
		try {
			userFile = createUserFile(cd);
			String uploadedFileLocation = getUploadedFileLocation(userFile);
			File uploadedFile = writeFile(inputStream, uploadedFileLocation);
			double size = (double) uploadedFile.length();
			userFile.setSize(size);
		} catch (IOException e) {
			logger.error(e);
		}
		updateFolders(userFile);
		dao.insert(userFile);
		return userFile;
	}

	public List<UserFile> uploadFiles(List<FileItem> items) {
		List<UserFile> files = new ArrayList<UserFile>();
		for (FileItem item : items) {
			UserFile file = uploadFile(item);
			files.add(file);
		}
		return files;

	}

	private String getUploadedFileLocation(UserFile userFile) {
		return userFile.getPath() + File.separator + userFile.getName();
	}

	private File writeFile(InputStream inputStream, String uploadedFileLocation)
			throws FileNotFoundException, IOException {
		File newFile = new File(uploadedFileLocation);
		OutputStream out = new FileOutputStream(newFile);
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = inputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
		return newFile;
	}

	private void updateFolders(UserFile file) {
		FolderService service = new FolderService();
		service.updateSize(file.getIdFolder(), file.getSize());
	}

	private UserFile createUserFile(FormDataContentDisposition cd) {
		FileService fileService = new FileService();
		UserService userService = new UserService();
		User user = userService.getUserById(folder.getIdUser());
		UserFile resultUserFile = new UserFile();
		Timestamp currentTS = CurrentTimeStamp.getCurrentTimeStamp();
		String incomeFileName = cd.getFileName();
		String fileName = new MD5Encrypter().encrypt(currentTS.toString()
				+ incomeFileName + user.getEmail());
		String filePath = fileService.getFolder().getAbsolutePath();
		String type = cd.getType();

		resultUserFile.setNameIncome(incomeFileName).setName(fileName)
				.setPath(filePath).setType(type).setSize(cd.getSize())
				.setDate(currentTS).setIdFolder(folder.getId())
				.setIdUser(user.getId());
		return resultUserFile;
	}

	private UserFile createUserFile(FileItem item) {
		UserService userService = new UserService();
		User user = userService.getUserById(folder.getIdUser());
		UserFile resultUserFile = new UserFile();

		String fileNameIncome = item.getName();
		Timestamp currentTS = CurrentTimeStamp.getCurrentTimeStamp();
		String fileName = new MD5Encrypter().encrypt(currentTS.toString()
				+ fileNameIncome + user.getEmail());
		FileService fileService = new FileService();
		String filePath = fileService.getFolder().getAbsolutePath();
		String contentType = item.getContentType();
		long fileSize = item.getSize();

		resultUserFile.setNameIncome(fileNameIncome).setName(fileName)
				.setPath(filePath).setType(contentType).setSize(fileSize)
				.setDate(currentTS).setIdFolder(folder.getId())
				.setIdUser(user.getId());
		return resultUserFile;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

}