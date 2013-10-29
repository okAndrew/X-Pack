package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.lab.controller.services.user.UserServiceImpl;

public class AdminUserFilesCommand implements AdminSimpleUserPageCommand {
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		UserServiceImpl userService = new UserServiceImpl();
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		boolean isBanned = userService.isBanned(userId);
		request.setAttribute("isbanned", isBanned);
		 request.setAttribute("parent", "adminUserFiles");
		// request.getRequestDispatcher("adminBrowserContent").include(request,
		// response);
		page = "adminUserFiles";
		return page;

	}

}
