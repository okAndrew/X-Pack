package com.epam.lab.controller.services.folder;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.folder.FolderDAO;
import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.dao.user.UserDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.file.UserFileService;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public class FolderServiceImpl extends AbstractServiceImpl<Folder> implements
		FolderService {
	private static final String ROOT_NAME = "root";
	private static final String SEPARATOR = "/";
	private FolderDAO folderDAO = (FolderDAO) dao;

	public FolderServiceImpl() {
		super(new FolderDAOImpl());
	}

	public Folder createFolder(String folderName, long userId, long upperId) {
		Folder folder = new Folder();
		Timestamp currentTS = new Timestamp(new Date().getTime());
		folder.setDate(currentTS).setIdUpper(upperId).setIdUser(userId)
				.setName(folderName).setSize(0);
		dao.insert(folder);
		return folder;
	}

	public Folder getRoot(long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder root = dao.getRoot(userId);
		if (root == null) {
			root = dao.get(createRoot(userId));
		}
		return root;
	}

	public List<Folder> getAll(long userId) {
		return folderDAO.getAll(userId);
	}

	public List<Folder> getByUpperId(long upperId) {
		List<Folder> resultList = folderDAO.getByUpperId(upperId);
		Collections.sort(resultList);
		return resultList;
	}

	public List<Folder> getFolderPath(long id) {
		List<Folder> folderPath = new LinkedList<Folder>();
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.get(id);
		while (folder.getIdUpper() != 0) {
			folderPath.add(0, folder);
			folder = dao.get(folder.getIdUpper());
		}
		folderPath.add(0, folder);
		return folderPath;
	}

	public long createRoot(long userId) {
		FolderDAOImpl folderDao = new FolderDAOImpl();
		Folder folder = folderDao.getRoot(userId);
		if (folder == null) {
			folder = new Folder();
			folder.setIdUser(userId).setName(ROOT_NAME).setIdUpper(0);
			folderDao.insert(folder);
			return getRoot(userId).getId();
		} else {
			return folder.getId();
		}
	}

	public Folder create(String path, long userId) {
		return create(path, userId, getRoot(userId).getId());
	}

	public Folder create(String path, long userId, long upperId) {
		List<String> pathList = getPathList(path);
		return create(pathList, userId, upperId);
	}

	public boolean check(String path, long userId) {
		return check(path, userId, getRoot(userId).getId());
	}

	public boolean check(String path, long userId, long upperId) {
		List<String> pathList = getPathList(path);
		return isFolderExist(pathList, userId, upperId);
	}

	public void updateSize(long folderId, long size) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.get(folderId);
		folder.setSize(folder.getSize() + size);
		dao.update(folder);
		if (folder.getIdUpper() != 0) {
			updateSize(folder.getIdUpper(), size);
		} else {
			UserDAOImpl userDAO = new UserDAOImpl();
			User user = userDAO.get(folder.getIdUser());
			user.setCapacity(folder.getSize());
			userDAO.update(user);
		}
	}

	public int delete(long id) {
		FolderDAOImpl dao = new FolderDAOImpl();
		UserFileServiceImpl fileService = new UserFileServiceImpl();
		FolderServiceImpl folderService = new FolderServiceImpl();
		List<UserFile> files = new FileDAOImpl().getAllByFolderId(id);
		for (UserFile file : files) {
			fileService.delete(file.getId());
		}
		List<Folder> folders = dao.getByUpperId(id);
		for (Folder folder : folders) {
			folderService.delete(folder.getId());
		}
		return dao.delete(id);
	}

	private List<String> getPathList(String path) {
		String[] resultArray = path.split(SEPARATOR);
		List<String> result = new LinkedList<String>();
		for (String s : resultArray) {
			result.add(s);
		}
		return result;
	}

	private Folder create(List<String> pathList, long userId, long upperId) {
		String folderName = pathList.get(0);
		pathList.remove(0);
		FolderDAOImpl dao = new FolderDAOImpl();
		List<Folder> folders = dao.getByUpperId(upperId);
		if (pathList.size() == 0) {
			for (Folder f : folders) {
				if (f.getName().equals(folderName)) {
					return f;
				}
			}
			return createFolder(folderName, userId, upperId);
		} else {
			for (Folder f : folders) {
				if (f.getName().equals(folderName)) {
					return create(pathList, userId, f.getId());
				}
			}
			Folder createdFolder = createFolder(folderName, userId, upperId);
			return create(pathList, userId, createdFolder.getId());
		}
	}

	private boolean isFolderExist(List<String> pathList, long userId,
			long upperId) {
		String folderName = pathList.get(0);
		pathList.remove(0);
		FolderDAOImpl dao = new FolderDAOImpl();
		List<Folder> folders = dao.getByUpperId(upperId);
		if (pathList.size() == 0) {
			for (Folder f : folders) {
				if (f.getName().equals(folderName)) {
					return true;
				}
			}
		} else {
			for (Folder f : folders) {
				if (f.getName().equals(folderName)) {
					return isFolderExist(pathList, userId, f.getId());
				}
			}
		}
		return false;
	}

	public void movefolder(long folderidmove, long folderidtarget) {
		FolderServiceImpl service = new FolderServiceImpl();
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.get(folderidmove);
		service.updateSize(folder.getIdUpper(), -folder.getSize());
		folder.setIdUpper(folderidtarget);
		service.updateSize(folderidtarget, folder.getSize());
		dao.update(folder);

	}

	@Override
	public void moveFilesAndFolders(String[] moveAble, long idTarget,
			long userId) {
		for (String move : moveAble) {
			String type = move.split("-")[0];
			long id = Long.parseLong(move.split("-")[1]);
			if (type.equals("file")) {
				UserFileServiceImpl fileService = new UserFileServiceImpl();
				if (fileService.isUsersFile(id, userId)) {
					fileService.moveFile(id, idTarget);
				}
			} else if (type.equals("folder")) {
				FolderServiceImpl folderService = new FolderServiceImpl();
				if (folderService.isUsersFolder(id, userId) && id != idTarget) {
					folderService.movefolder(id, idTarget);
				}
			}
		}
	}

	@Override
	public int deleteByUserId(long userId) {
		FolderDAOImpl folderDao = new FolderDAOImpl();
		int result = folderDao.deleteByUserId(userId);
		return result;
	}

	public boolean isUsersFolder(long id, long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.get(id);
		if (folder.getIdUser() == userId) {
			return true;
		} else {
			return false;
		}
	}

	public Folder makeFolder(String folderName, long userId, long upperId) {
		Folder resultFolder = null;
		if (Validator.FILE_NAME.validate(folderName)
				&& !checkFolderExist(folderName, upperId)) {
			resultFolder = createFolder(folderName, userId, upperId);
		}
		return resultFolder;
	}

	private boolean checkFolderExist(String folderName, long upperId) {
		boolean result = false;
		FolderDAOImpl dao = new FolderDAOImpl();
		List<Folder> folders = dao.getByUpperId(upperId);
		for (Folder folder : folders) {
			if (folder.getName().equals(folderName)) {
				result = true;
			}
		}
		return result;
	}

	public void refresh(long userId) {
		Folder root = getRoot(userId);
		long size = refreshSizes(root.getId());
		root.setSize(size);
		folderDAO.update(root);
	}

	private long refreshSizes(long id) {
		long result = 0;
		UserFileService fileService = new UserFileServiceImpl();
		fileService.refresh(id);
		List<Folder> subFolders = folderDAO.getByUpperId(id);
		for (Folder subFolder : subFolders) {
			long size = refreshSizes(subFolder.getId());
			subFolder.setSize(size);
			folderDAO.update(subFolder);
			result += size;
		}
		List<UserFile> files = fileService.getByFolderId(id);
		for (UserFile file : files) {
			result += file.getSize();
		}
		return result;
	}
}