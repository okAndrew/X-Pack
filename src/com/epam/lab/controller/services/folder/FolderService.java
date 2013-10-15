package com.epam.lab.controller.services.folder;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.controller.dao.impl.FolderDAOImpl;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;

public class FolderService {
	private static final String ROOT_NAME = "root";

	public void createRootFolder(long userId) {
		FolderDAOImpl folderDao = new FolderDAOImpl();
		Folder folder = folderDao.getRootFolderByUserId(userId);
		if (folder == null) {
			folder = new Folder();
			folder.setIdUser(userId).setName(ROOT_NAME).setIdUpper(0);
			folderDao.insert(folder);
		}
	}

	public long getRootId(long userId) {
		return new FolderDAOImpl().getRootFolderByUserId(userId).getId();
	}

	public List<Folder> getFolders(long userId, long folderId) {
		List<Folder> folders = null;
		FolderDAOImpl foldersdaoimpl = new FolderDAOImpl();
		folders = foldersdaoimpl.getAllbyUserIdAndUpperId(userId, folderId);
		return folders;
	}

	public void createFolder(String name, long userId, long upperId) {
		Folder folder = new Folder();
		folder.setIdUser(userId).setName(name).setIdUpper(upperId);
		FolderDAOImpl folderDao = new FolderDAOImpl();
		folderDao.insert(folder);
	}

	public void delete(long id, long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		FileService fileService = new FileService();
		FolderService folderService = new FolderService();
		List<File> files = new FileDAOImpl().getAllbyUserIdAndFolderId(userId,
				id);
		for (File file : files) {
			fileService.delete(file.getId());
		}
		List<Folder> folders = dao.getAllbyUserIdAndUpperId(userId, id);
		for (Folder folder : folders) {
			folderService.delete(folder.getId(), userId);
		}
		dao.delete(id);
	}

	public Folder getFolder(long id) {
		return new FolderDAOImpl().get(id);
	}

	public boolean isFolderExist(long upperId, String folderName) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.getFolderByNameAndUpperId(upperId, folderName);
		if (folder == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<Folder> getFolders(long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		return dao.getAllbyUserId(userId);
	}

	public List<Folder> getSearchedFolders(long userId, String text) {
		FolderService service = new FolderService();
		List<Folder> folders = service.getFolders(userId);
		List<Folder> result = new ArrayList<Folder>();
		for (Folder folder : folders) {
			if (folder.getName().contains(text)) {
				result.add(folder);
			}
		}
		return result;
	}
}
