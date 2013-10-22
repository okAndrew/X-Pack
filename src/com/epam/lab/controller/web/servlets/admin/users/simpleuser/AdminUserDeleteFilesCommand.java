package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.folder.FolderServiceImpl;

public class AdminUserDeleteFilesCommand implements AdminUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserFileServiceImpl service2 = new UserFileServiceImpl();
		String[] rs = request.getParameterValues("files");
		String[] rs2 = request.getParameterValues("folders");
		if (rs != null) {
			for (int i = 0; i < rs.length; i++) {
				service2.delete(Integer.parseInt(rs[i]));
			}
		}
		if (rs2 != null) {
			FolderServiceImpl service = new FolderServiceImpl();
			for (int i = 0; i < rs2.length; i++) {
				service.delete(Integer.parseInt(rs2[i]));
			}
		}
		if (rs == null && rs2 == null) {
			request.setAttribute("message",
					"Error! Please select files to delete");
		}
		page = "adminUserFiles";
		return page;
	}

}
