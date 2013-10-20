package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

public class AdminActivateUsersCommand implements AdminUsersPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		String message = null;
		String[] chekedUsers = request.getParameterValues("checkUser");
		UserServiceImpl service = new UserServiceImpl();
		message = service.activateUsers(chekedUsers);
		request.setAttribute("message", message);
		page = "adminUsersPage";
		return page;
	}

}
