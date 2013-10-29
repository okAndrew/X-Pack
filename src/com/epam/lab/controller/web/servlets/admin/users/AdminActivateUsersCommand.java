package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

public class AdminActivateUsersCommand implements AdminUsersPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		if (request.getParameterValues("checkUser") == null) {
			request.setAttribute("message", "Please check users!!!");
			page = "adminUsersPage";
			return page;
		} else {
			UserServiceImpl service = new UserServiceImpl();
			Long adminId = (Long) request.getSession(false).getAttribute("userid");
			service.cancelBanUsers(request.getParameterValues("checkUser"), adminId);
			page = "adminUsersPage";
		}
		return page;
	}
}