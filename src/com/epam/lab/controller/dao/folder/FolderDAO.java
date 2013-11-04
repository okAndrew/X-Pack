package com.epam.lab.controller.dao.folder;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Folder;

public interface FolderDAO extends GenericDAO<Folder> {

	int deleteByUserId(long userId);

	Folder getByUpperIdAndName(long upperId, String folderName);

	List<Folder> getByUpperId(long upperId);

	List<Folder> getAll(long userId);

	Folder getRoot(long userId);
}
