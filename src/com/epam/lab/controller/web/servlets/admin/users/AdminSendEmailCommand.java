package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

public class AdminSendEmailCommand implements AdminUsersPageCommand {

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
			String subject = request.getParameter("subject");
			String message = request.getParameter("body");
			service.sendUsersEmail(request.getParameterValues("checkUser"), subject, message);
			page = "adminUsersPage";
		}
		return page;
	}

}