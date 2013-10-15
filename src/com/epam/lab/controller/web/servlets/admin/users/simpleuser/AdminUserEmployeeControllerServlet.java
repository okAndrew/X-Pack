package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userEmployeeController")
public class AdminUserEmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_USER_DELETE_FILES = "adminUserDeleteFile";
	private static String ADMIN_USER_SEARCH_FILES = "adminUserSearchFiles";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("search")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ADMIN_USER_SEARCH_FILES);
			dispatcher.forward(request, response);
		} else if (action.equals("delete")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ADMIN_USER_DELETE_FILES);
			dispatcher.forward(request, response);
		}
	}
}
