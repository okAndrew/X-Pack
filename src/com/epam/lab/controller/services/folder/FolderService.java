package com.epam.lab.controller.services.folder;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.controller.dao.impl.FileDAOImpl;
import com.epam.lab.controller.dao.impl.FolderDAOImpl;
import com.epam.lab.controller.services.file.FileService;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.model.File;
import com.epam.lab.model.Folder;

public class FolderService {
	private static final String ROOT_NAME = "root";
	private static final String SEPARATOR = java.io.File.separator;

	public long createRootFolder(long userId) {
		FolderDAOImpl folderDao = new FolderDAOImpl();
		Folder folder = folderDao.getRootFolder(userId);
		if (folder == null) {
			folder = new Folder();
			folder.setIdUser(userId).setName(ROOT_NAME).setIdUpper(0);
			folderDao.insert(folder);
			return getRootId(userId);
		} else {
			return folder.getId();
		}
	}

	public long getRootId(long userId) {
		return new FolderDAOImpl().getRootFolder(userId).getId();
	}

	public List<Folder> getFolders(long userId, long folderId) {
		List<Folder> folders = null;
		FolderDAOImpl foldersdaoimpl = new FolderDAOImpl();
		folders = foldersdaoimpl.getAllbyUserIdAndUpperId(userId, folderId);
		return folders;
	}

	public void createFolder(String name, long userId, long upperId) {
		Folder folder = new Folder();
		folder.setIdUser(userId).setName(name).setIdUpper(upperId)
				.setDate(CurrentTimeStamp.getCurrentTimeStamp()).setSize(0);
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

	public boolean checkFolder(String path, long userId, long upperId) {
		List<String> pathList = getPathList(path);
		return isFolderExist(pathList, userId, upperId);
	}

	public boolean checkFolder(String path, long userId) {
		return checkFolder(path, userId, 0);
	}

	private boolean isFolderExist(List<String> pathList, long userId,
			long upperId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		List<Folder> folders = dao.getFoldersByUpperId(upperId);
		if (pathList.size() <= 1) {
			for (Folder folder : folders) {
				if (pathList.get(0).equals(folder.getName())) {
					return true;
				}
			}
		} else {
			for (String p : pathList) {
				boolean isCurFolder = false;
				Folder f = null;
				for (Folder folder : folders) {
					if (p.equals(folder.getName())) {
						isCurFolder = true;
						f = folder;
						break;
					}
				}
				if (isCurFolder && f != null) {
					pathList.remove(p);
					return isFolderExist(pathList, userId, f.getId());
				}
			}
		}
		return false;
	}

	public List<Folder> getFolders(long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		return dao.getAll(userId);
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

	public void updateFoldersSize(long idFolder, double size) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.get(idFolder);
		folder.setSize(folder.getSize() + size);
		dao.update(folder);
		if (folder.getIdUpper() != 0) {
			updateFoldersSize(folder.getIdUpper(), size);
		}
	}

	private List<String> getPathList(String path) {
		String[] resultArray = path.split(SEPARATOR + SEPARATOR);
		List<String> result = new ArrayList<String>();
		for (String s : resultArray) {
			result.add(s);
		}
		return result;
	}
}
