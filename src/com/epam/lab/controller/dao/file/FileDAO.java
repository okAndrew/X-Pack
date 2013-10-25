package com.epam.lab.controller.dao.file;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.FilesTypesSize;
import com.epam.lab.model.UserFile;

public interface FileDAO extends GenericDAO<UserFile> {

	List<FilesTypesSize> getFilesGroupType();

	UserFile getByName(String fName);
	
	int deleteByUserId(long userId);
}