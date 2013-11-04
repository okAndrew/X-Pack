package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

public class AdminDeleteUsersCommand implements AdminUsersPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		if (request.getParameterValues("checkUser") == null) {
			request.setAttribute("message", "Please_check_users");
			page = "adminUsersPage";
			return page;
		} else {
			UserServiceImpl service = new UserServiceImpl();
			Long adminId = (Long) request.getSession().getAttribute("userid");
			service.deleteUsers(request.getParameterValues("checkUser"),
					adminId);
			page = "adminUsersPage";
		}
		return page;
	}
}