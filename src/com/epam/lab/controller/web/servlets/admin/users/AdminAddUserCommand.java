package com.epam.lab.controller.web.servlets.admin.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.RegistrationService;

public class AdminAddUserCommand implements AdminUsersPageCommand {

	private static Logger logger = Logger.getLogger(AdminAddUserCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = null;
		String errorMessage = null;
		RegistrationService service = new RegistrationService();
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		errorMessage = service.regUser(login, email, password);
		if (errorMessage == null) {
			page = "adminUsersPage";
			logger.info("user by email" + email + "add to databases");
		} else {
			request.setAttribute("message", errorMessage);
			page = "WEB-INF/jsp/admin/users/addUserModalPage.jsp";
			logger.error("user by email" + email + "had error to create acount");
		}
		return page;
	}

}
