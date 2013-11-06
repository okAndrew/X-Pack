package com.epam.lab.controller.services.user;

import com.epam.lab.controller.exceptions.notfound.FolderNotFoundException;
import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.User;

public interface UserService extends AbstractService<User> {

	User getUserByFolderId(long idFolder) throws FolderNotFoundException;

	void deleteUsers(String[] usersId, Long idAdmin);

	void activateUsers(String[] usersId, Long idAdmin);

	void banedUsers(String[] usersId, Long idAdmin);

	void cancelBanUsers(String[] usersId, Long idAdmin);

	void deactivateOverdue();

	void deleteFiles(String[] filesId, Long userId, String message);

	void setLastLocale(String locale, long userId);

}
