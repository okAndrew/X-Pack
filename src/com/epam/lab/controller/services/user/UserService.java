package com.epam.lab.controller.services.user;

import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.User;

public interface UserService extends AbstractService<User> {
	public User getUserByFolderId(long idFolder) throws FolderNotFoundException;
}
