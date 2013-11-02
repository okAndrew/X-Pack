package com.epam.lab.controller.services.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public class FileUploadServiceImpl implements FileUploadService {
	private static Logger logger = Logger
			.getLogger(FileUploadServiceImpl.class);

	@Override
	public void uploadUserFiles(Long folderId, Long userId, List<FileItem> items)
			throws FileTooLargeException, IOException {
		FileUploadServiceImpl uploadService = new FileUploadServiceImpl();
		UserFileService fileService = new UserFileServiceImpl();
		User user = new UserServiceImpl().get(userId);
		for (FileItem fileItem : items) {
			UserFile fileInfo = fileService.createFileInfo(fileItem.getName(),
					folderId, userId, false, fileItem.getSize());
			uploadService.uploadFile(fileItem, fileInfo, user);
		}
	}

	@Override
	public void uploadFile(FileItem fileItem, UserFile fileInfo, User user)
			throws FileTooLargeException, IOException {
		long maxCapacity = new UserServiceImpl().getFreeSize(user.getId());
		if (user.getCapacity() + fileItem.getSize() > maxCapacity) {
			throw new FileTooLargeException();
		}
		File uploadedFile = new File(getUploadFileLocation(fileInfo));
		try {
			fileItem.write(uploadedFile);
		} catch (Exception e) {
			logger.error(e);
			throw new IOException();
		}
		fileInfo.setSize(uploadedFile.length());
		updateFileSizeInfo(fileInfo);
	}

	@Override
	public void uploadFile(InputStream inputStream, UserFile fileInfo, User user)
			throws IOException, FileTooLargeException {
		long maxCapacity = new UserServiceImpl().getFreeSize(user.getId());
		File uploadedFile = writeFile(inputStream,
				getUploadFileLocation(fileInfo), maxCapacity);
		fileInfo.setSize(uploadedFile.length());
		updateFileSizeInfo(fileInfo);
	}

	private void updateFileSizeInfo(UserFile file) {
		new FileDAOImpl().updateSize(file.getId(), file.getSize());
		new FolderServiceImpl().updateSize(file.getIdFolder(), file.getSize());
	}

	private String getUploadFileLocation(UserFile userFile) {
		return userFile.getPath() + File.separator + userFile.getName();
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
}
