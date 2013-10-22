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

import com.epam.lab.controller.dao.file.FileDAO;
import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.FileType;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public class UserFileUploader {
	private static Logger logger = Logger.getLogger(UserFileUploader.class);

	public UserFile uploadFile(FileItem fileItem, String fileIncomeName,
			long idFolder) throws FolderNotFoundException, IOException {
		UserFile userFile = null;
		userFile = createUserFile(fileIncomeName, idFolder);
		String uploadedFileLocation = getUploadFileLocation(userFile);
		try {
			fileItem.write(new File(uploadedFileLocation));
		} catch (Exception e) {
			logger.error(e);
			throw new IOException();
		}
		userFile.setSize(fileItem.getSize());

		executeActionUploadInDB(userFile);
		return userFile;
	}

	public UserFile uploadFile(InputStream inputStream, String fileIncomeName,
			long idFolder) throws FolderNotFoundException, IOException {
		UserFile userFile = null;
		userFile = createUserFile(fileIncomeName, idFolder);
		String uploadedFileLocation = getUploadFileLocation(userFile);
		File uploadedFile = writeFile(inputStream, uploadedFileLocation);
		if (uploadedFile == null)
			return null;
		userFile.setSize(uploadedFile.length());

		executeActionUploadInDB(userFile);
		return userFile;
	}

	public List<UserFile> uploadFiles(List<FileItem> items, long idFolder)
			throws FolderNotFoundException {
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

	private String getUploadFileLocation(UserFile userFile) {
		return userFile.getPath() + File.separator + userFile.getName();
	}

	private void updateFolders(UserFile file) {
		FolderService service = new FolderServiceImpl();
		service.updateSize(file.getIdFolder(), file.getSize());
	}

	private void executeActionUploadInDB(UserFile userFile) {
		FileDAO dao = new FileDAOImpl();
		updateFolders(userFile);
		dao.insert(userFile);
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
		Timestamp currentTS = CurrentTimeStamp.getCurrentTimeStamp();

		String fileName = new MD5Encrypter().encrypt(currentTS.toString()
				+ userFile.getNameIncome() + user.getEmail());

		String filePath = new UserFileServiceImpl().getFolder()
				.getAbsolutePath();
		userFile.setName(fileName).setPath(filePath).setDate(currentTS)
				.setIdFolder(folder.getId()).setIdUser(user.getId());
	}
}