package com.epam.lab.controller.services.folder;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.controller.dao.folder.FolderDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

public class FolderServiceImpl extends AbstractServiceImpl<Folder> implements
		FolderService {
	private static final String ROOT_NAME = "root";
	private static final String SEPARATOR = "/";
	private Timestamp currentTS = CurrentTimeStamp.getCurrentTimeStamp();

	public FolderServiceImpl() {
		super(new FolderDAOImpl());
	}

	public Folder getRoot(long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder root = dao.getRoot(userId);
		if (root == null) {
			root = dao.get(createRoot(userId));
		}
		return root;
	}

	public List<Folder> get(long userId, long upperId) {
		List<Folder> folders = null;
		FolderDAOImpl foldersdaoimpl = new FolderDAOImpl();
		folders = foldersdaoimpl.getByUpperId(upperId);
		return folders;
	}

	public List<Folder> getAll(long userId) {
		FolderDAOImpl dao = new FolderDAOImpl();
		return dao.getAll(userId);
	}

	public List<Folder> getSearched(long userId, String text) {
		List<Folder> folders = getAll(userId);
		List<Folder> result = new ArrayList<Folder>();
		for (Folder folder : folders) {
			if (folder.getName().contains(text)) {
				result.add(folder);
			}
		}
		return result;
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

	public long create(String path, long userId) {
		return create(path, userId, getRoot(userId).getId());
	}

	public long create(String path, long userId, long upperId) {
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

	public void updateSize(long folderId, double size) {
		FolderDAOImpl dao = new FolderDAOImpl();
		Folder folder = dao.get(folderId);
		folder.setSize(folder.getSize() + size);
		dao.update(folder);
		if (folder.getIdUpper() != 0) {
			updateSize(folder.getIdUpper(), size);
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

	private long create(List<String> pathList, long userId, long upperId) {
		String folderName = pathList.get(0);
		pathList.remove(0);
		FolderDAOImpl dao = new FolderDAOImpl();
		List<Folder> folders = dao.getByUpperId(upperId);
		if (pathList.size() == 0) {
			for (Folder f : folders) {
				if (f.getName().equals(folderName)) {
					return f.getId();
				}
			}
			return createFolder(folderName, userId, upperId);
		} else {
			for (Folder f : folders) {
				if (f.getName().equals(folderName)) {
					return create(pathList, userId, f.getId());
				}
			}
			return create(pathList, userId,
					createFolder(folderName, userId, upperId));
		}
	}

	private long createFolder(String folderName, long userId, long upperId) {
		Folder folder = new Folder();
		folder.setDate(currentTS).setIdUpper(upperId).setIdUser(userId)
				.setName(folderName).setSize(0);
		FolderDAOImpl dao = new FolderDAOImpl();
		dao.insert(folder);
		folder = dao.getByUpperIdAndName(upperId, folderName);
		return folder.getId();
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
}