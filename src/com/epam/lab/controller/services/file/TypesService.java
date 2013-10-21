package com.epam.lab.controller.services.file;

import java.util.List;

import com.epam.lab.controller.dao.file.FileDAOImpl;
import com.epam.lab.model.FilesTypesSize;

// what is it???
public class TypesService {

	private FileDAOImpl fileDaoImpl = new FileDAOImpl();

	public List<FilesTypesSize> getTypesFiles() {
		return fileDaoImpl.getFilesGroupType();
	}

}
