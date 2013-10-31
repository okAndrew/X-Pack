package com.epam.lab.controller.services.user;

import java.util.List;

import com.epam.lab.model.Folder;
import com.epam.lab.model.UserFile;

public interface SearchService {
	boolean prepareLists(long upperId, String searched);
	List<Folder> getFolders();
	List<UserFile> getFiles();
}