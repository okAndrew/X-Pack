package com.epam.lab.controller.services.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.epam.lab.controller.exceptions.FileTooLargeException;
import com.epam.lab.model.User;
import com.epam.lab.model.UserFile;

public interface FileUploadService {

	void uploadUserFiles(Long folderId, Long userId, List<FileItem> items)
			throws FileTooLargeException, IOException;

	void uploadFile(FileItem fileItem, UserFile fileInfo, User user)
			throws FileTooLargeException, IOException;

	void uploadFile(InputStream inputStream, UserFile fileInfo, User user)
			throws IOException, FileTooLargeException;

}
