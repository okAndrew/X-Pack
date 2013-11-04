package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.AdminSimpleUserPageCommand;

public class AdminSimpleUserFilesDelCommand implements
		AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserServiceImpl service = new UserServiceImpl();
		Long userId = (Long) request.getSession().getAttribute("adminUserid");
		String messageToUser = request.getParameter("message");
		service.deleteFiles(request.getParameterValues("filelist"), userId,
				messageToUser);
		page = "adminUserFiles";

		return page;
	}

}
