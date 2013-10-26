package com.epam.lab.controller.services.user;

import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.User;

public interface UserService extends AbstractService<User> {
	
	User getUserByFolderId(long idFolder) throws FolderNotFoundException;
	
	void deleteUsers(String[] usersId);
	
	void activateUsers(String[] usersId);
	
	void banedUsers(String[] usersId);
	
	void cancelBanUsers(String[] usersId);
	
	void deactivateOverdue();
	
}
