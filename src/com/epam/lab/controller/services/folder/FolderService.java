package com.epam.lab.controller.services.folder;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Folder;

public interface FolderService extends AbstractService<Folder> {

	void updateSize(long idFolder, long size);

	int deleteByUserId(long userId);

	Folder getRoot(long userId);

	List<Folder> getFolderPath(long id);
}
