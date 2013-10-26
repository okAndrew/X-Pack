package com.epam.lab.controller.web.servlets.admin.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/employeeControllerUsers")
public class AdminUsersEmployeeControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(AdminUsersEmployeeControllerServlet.class);
	private static String ADMIN_USERS_PAGE = "adminUsersPage";
	private AdminUsersRequestHelper requestHelper = AdminUsersRequestHelper
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
		AdminUsersPageCommand command = requestHelper.getCommand(request);
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
