
package com.epam.lab.controller.dao;

import java.util.List;

import com.epam.lab.model.FilesTypesSize;
import com.epam.lab.model.UserFile;

public interface FileDAO extends GenericDAO<UserFile> {

	List<FilesTypesSize> getFilesGroupType();
}