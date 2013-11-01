package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.AdminSimpleUserPageCommand;

public class AdminSimpleUserFilesDelCommand implements AdminSimpleUserPageCommand{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserFileServiceImpl service2 = new UserFileServiceImpl();
		String[] files = request.getParameterValues("files");
		String[] folders = request.getParameterValues("folders");
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				service2.delete(Integer.parseInt(files[i]));
			}
		}
		if (folders != null) {
			FolderServiceImpl service = new FolderServiceImpl();
			for (int i = 0; i < folders.length; i++) {
				service.delete(Integer.parseInt(folders[i]));
			}
		}
		if (files == null && folders == null) {
			request.setAttribute("message",
					"Error! Please select files to delete");
		}
		page = "adminUserFiles";
		return page;
	}

}
