package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;

public class AdminBanedUsersCommand implements AdminUsersPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		String message = null;
		String[] chekedUsers = request.getParameterValues("checkUser");
		UserService service = new UserService();
		message = service.deactivateUsers(chekedUsers);
		request.setAttribute("message", message);
		page = "adminUsersPage";
		return page;
	}
}