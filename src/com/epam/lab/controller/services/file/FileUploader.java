package com.epam.lab.controller.services.file;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.controller.dao.impl.FolderDAOImpl;
import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;

public class FileUploader {
	private static final Logger logger = Logger.getLogger(FileUploader.class);
	private List<FileItem> items = null;
	private User user = null;
	private Folder folder = null;

	public FileUploader(List<FileItem> items, long userId) {
		this.items = items;
		this.user = new UserDAOImpl().get(userId);
	}

	public void run() {
		Iterator<FileItem> iter = items.iterator();
		List<File> files = new ArrayList<File>();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
				if (item.getFieldName().equals("folderId")) {
					try {
						folder = new FolderDAOImpl().get(Long.valueOf(item
								.getString("UTF8")));
					} catch (UnsupportedEncodingException e) {
						logger.error(e);
						e.printStackTrace();
					}
				}
			} else if (item.getSize() > 0) {
				File file = getFile(item);
				java.io.File f = new java.io.File(file.getPath()
						+ java.io.File.separator + file.getName());
				try {
					item.write(f);
				} catch (Exception e) {
				}
				files.add(file);
			}
		}
		if (folder != null) {
			for (File file : files) {
				file.setIdFolder(folder.getId());
				new FileDAOImpl().insert(file);
			}
		} else {
			for (File file : files) {
				new FileDAOImpl().insert(file);
			}
		}
	}

	private File getFile(FileItem item) {
		FileService service = new FileService();
		File file = new File();
		String fileNameIncome = item.getName();
		String userLogin = null;
		if (user != null) {
			userLogin = user.getLogin();
		}
		String fileName = service.getFileName(fileNameIncome, userLogin);
		String filePath = service.getFolder().getAbsolutePath();
		String contentType = item.getContentType();
		long fileSize = item.getSize();
		file.setNameIncome(fileNameIncome)
				.setName(fileName)
				.setPath(filePath)
				.setType(contentType)
				.setSize(fileSize);
		if (user != null) {
			file.setIdUser(user.getId());
		}
		return file;
	}

}