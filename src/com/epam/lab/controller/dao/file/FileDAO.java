package com.epam.lab.controller.dao.file;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.FilesTypesSize;
import com.epam.lab.model.UserFile;

public interface FileDAO extends GenericDAO<UserFile> {

	UserFile getByName(String fName);

	UserFile getSizeUploadByDates(Timestamp dateStart, Timestamp dateEnd);

	UserFile getSizeUploadUserByDates(Timestamp dateStart, Timestamp dateEnd, long userId);

	List<FilesTypesSize> getFilesGroupType();

	List<UserFile> getAllByFolderId(long folderId);

	List<UserFile> getAllByUserId(long userId);

	int updateSize(long id, long size);

	int deleteByUserId(long userId);

	int deleteByFolderId(long id);
}