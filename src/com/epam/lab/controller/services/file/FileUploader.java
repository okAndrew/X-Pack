package com.epam.lab.controller.services.file;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.controller.dao.impl.FolderDAOImpl;
import com.epam.lab.controller.dao.impl.UserDAOImpl;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.services.folder.FolderService;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.controller.utils.MD5Encrypter;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;

public class FileUploader {
	private List<FileItem> items = null;
	private User user = null;
	private Folder folder = null;
	private Timestamp currentTS = null;

	public FileUploader(List<FileItem> items, long userId, long folderId) {
		this.items = items;
		this.user = new UserDAOImpl().get(userId);
		this.folder = new FolderDAOImpl().get(folderId);
		this.currentTS = CurrentTimeStamp.getCurrentTimeStamp();
	}

	public void run() {
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			FileDAOImpl dao = new FileDAOImpl();
			if (!item.isFormField() && item.getSize() > 0) {
				File file = getFile(item);
				java.io.File f = new java.io.File(file.getPath()
						+ java.io.File.separator + file.getName());
				try {
					item.write(f);
				} catch (Exception e) {
				}
				updateFolders(file);
				dao.insert(file);
			}
		}
	}

	private void updateFolders(File file) {
		FolderService service = new FolderService();
		service.updateFoldersSize(file.getIdFolder(), file.getSize());
	}

	private File getFile(FileItem item) {
		FileService service = new FileService();
		File file = new File();
		String fileNameIncome = item.getName();
		String userLogin = null;
		if (user != null) {
			userLogin = user.getLogin();
		}
		String fileName = new MD5Encrypter().encrypt(currentTS.toString()
				+ fileNameIncome + userLogin);
		String filePath = service.getFolder().getAbsolutePath();
		String contentType = item.getContentType();
		long fileSize = item.getSize();
		file.setNameIncome(fileNameIncome).setName(fileName).setPath(filePath)
				.setType(contentType).setSize(fileSize).setDate(currentTS);
		if (user != null) {
			file.setIdUser(user.getId());
		}
		if (folder != null) {
			file.setIdFolder(folder.getId());
		}
		return file;
	}
}