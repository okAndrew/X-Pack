package com.epam.lab.controller.services.file;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.UserFile;

public interface UserFileService extends AbstractService<UserFile> {
	public UserFile createFileInfo(String nameIncome, Long idFolder,
			Long idUser, Boolean isPublic, Long size);

	public List<UserFile> getByFolderId(long folderId);

	public int deleteByUserId(long userId);

	public long getUploadTrafficByDates(Timestamp dataStart, Timestamp dataEnd);

	public long getUploadTrafficUserByDates(Timestamp dataStart,
			Timestamp dataEnd, long userId);

	public void changePublicState(long id, boolean state);
}
