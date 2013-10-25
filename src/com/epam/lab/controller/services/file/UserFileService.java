package com.epam.lab.controller.services.file;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.UserFile;

public interface UserFileService extends AbstractService<UserFile> {

	List<UserFile> getByFolderId(long folderId);

	int deleteByUserId(long userId);
}
