package com.epam.lab.controller.services.user;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

public class SearchServiceImpl implements SearchService {
	private UserFileServiceImpl fileService = new UserFileServiceImpl();
	private FolderServiceImpl folderService = new FolderServiceImpl();
	private List<Folder> folders;
	private List<UserFile> files = new ArrayList<UserFile>();

	@Override
	public boolean prepareLists(long upperId, String searched) {
		this.folders = find(upperId, searched);
		if ((folders != null && folders.size() > 0)
				|| (files != null && files.size() > 0)) {
			return true;
		} else
			return false;
	}

	@Override
	public List<Folder> getFolders() {
		return this.folders;
	}

	@Override
	public List<UserFile> getFiles() {
		return this.files;
	}

	private List<Folder> find(long upperId, String searched) {
		List<Folder> result = new ArrayList<Folder>();
		this.files.addAll(findFiles(upperId, searched));
		List<Folder> folders = folderService.getByUpperId(upperId);
		for (Folder folder : folders) {
			if (folder.getName().contains(searched)) {
				result.add(folder);
			}
			result.addAll(find(folder.getId(), searched));
		}
		return result;
	}
	
	private List<UserFile> findFiles(long folderId, String searched) {
		List<UserFile> files = fileService.getByFolderId(folderId);
		List<UserFile> result = new ArrayList<UserFile>();
		for (UserFile file : files) {
			if (file.getNameIncome().contains(searched)) {
				result.add(file);
			}
		}
		return result;
	}
}