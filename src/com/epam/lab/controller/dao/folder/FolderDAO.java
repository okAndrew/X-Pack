package com.epam.lab.controller.dao.folder;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Folder;

public interface FolderDAO extends GenericDAO<Folder> {

	public int deleteByUserId(long userId);

	public Folder getByUpperIdAndName(long upperId, String folderName);

	public List<Folder> getByUpperId(long upperId);

	public List<Folder> getAll(long userId);
}
