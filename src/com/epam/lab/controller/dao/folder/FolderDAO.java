
package com.epam.lab.controller.dao.folder;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Folder;

public interface FolderDAO extends GenericDAO<Folder>{
	
	int deleteByUserId(long userId);
}

