package com.epam.lab.controller.services.file;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.UserFile;

public interface UserFileService extends AbstractService<UserFile> {
	UserFile createFileInfo(String nameIncome, Long idFolder, Long idUser,
			Boolean isPublic, Long size);

	List<UserFile> getByFolderId(long folderId);

	int deleteByUserId(long userId);

	long getUploadTrafficByDates(Timestamp dataStart, Timestamp dataEnd);

	long getUploadTrafficUserByDates(Timestamp dataStart, Timestamp dataEnd,
			long userId);

	void changePublicState(long id, boolean state);

	String getLink(long fileId);

	void refresh(long folderId);
}