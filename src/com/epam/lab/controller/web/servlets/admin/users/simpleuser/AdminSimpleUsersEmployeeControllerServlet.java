package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/adminSimpleEmployeeController")
public class AdminSimpleUsersEmployeeControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(AdminSimpleUsersEmployeeControllerServlet.class);
	private static String ADMIN_USERS = "adminUser";
	private AdminSimpleUsersRequestHelper requestHelper = AdminSimpleUsersRequestHelper
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
		AdminSimpleUserPageCommand command = requestHelper.getCommand(request);
		if (command == null) {
			logger.error("Command not found in adminUsersPage");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ADMIN_USERS);
			dispatcher.forward(request, response);
		} else {
			page = command.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
}
