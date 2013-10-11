package com.epam.lab.controller.services.folder;

import java.util.List;

import com.epam.lab.controller.dao.impl.FolderDAOImpl;
import com.epam.lab.model.Folder;


public class FolderService {
	private static final String ROOT_NAME = "root";

	public static List<Folder> getFolders(long iduser, long idFolder){
		List<Folder> folders = null;
		FolderDAOImpl foldersdaoimpl = new FolderDAOImpl();
		folders = foldersdaoimpl.getAllbyUserIdAndUpperId(iduser, idFolder);
		return folders;
	}

	public static void createRootFolder(int userId) {
		Folder folder = new Folder();
		folder.setIdUser(userId).setName(ROOT_NAME).setIdUpper(0);
		FolderDAOImpl folderDao = new FolderDAOImpl();
		folderDao.insert(folder);
	}

	public static long getRootId(long userId) {
		return new FolderDAOImpl().getRootFolderByUserId(userId).getId();
	}

	public static void createFolder(String name, long userId, long upperId) {
		Folder folder = new Folder();
		folder.setIdUser(userId).setName(name).setIdUpper(upperId);
		FolderDAOImpl folderDao = new FolderDAOImpl();
		folderDao.insert(folder);
	}

	public static void delete(long id) {
		FolderDAOImpl dao = new FolderDAOImpl();
		dao.delete(id);
	}

	public static Folder getFolderById(long id) {
		return new FolderDAOImpl().get(id);
	}

	public static boolean isFolderExist(long upperId, String folderName) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.getFolderByNameAndUpperId(upperId, folderName);
		if (folder==null) {
			return false;
		} else {
			return true;
		}
	}
}
