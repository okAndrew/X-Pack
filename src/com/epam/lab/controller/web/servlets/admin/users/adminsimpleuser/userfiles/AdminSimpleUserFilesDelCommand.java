package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userfiles;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;

public class AdminSimpleUserFilesDelCommand implements
		AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserServiceImpl service = new UserServiceImpl();
		Long userId = (Long) request.getSession().getAttribute("adminUserid");
		String messageToUser = request.getParameter("message");
		service.deleteFiles(request.getParameterValues("files"), userId,
				messageToUser);
		page = "adminUser?page=adminUserFiles";
		return page;
	}

}
