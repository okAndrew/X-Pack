package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userfiles;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;

@WebServlet("/adminUsercontroller")
public class AdminUserControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(AdminUserControllerServlet.class);
	private static String ADMIN_USERS_PAGE = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
	private AdminSimpleUserRequestHelperFile requestHelper = AdminSimpleUserRequestHelperFile
			.getInstance();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		AdminSimpleUserPageCommand command = requestHelper
				.parseCommand(request);
		if (command == null) {
			logger.error("Command not found in adminUsersPage");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ADMIN_USERS_PAGE);
			dispatcher.forward(request, response);
		} else {
			page = command.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

}
