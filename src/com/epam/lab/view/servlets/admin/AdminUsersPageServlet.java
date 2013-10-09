package com.epam.lab.view.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;

@WebServlet("/adminUsersPage")
public class AdminUsersPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USERS_PAGE_JSP = "WEB-INF/jsp/admin/adminUsersPage.jsp";

	public AdminUsersPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getUsers(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USERS_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getUsers(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USERS_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	private void getUsers(HttpServletRequest request,
			HttpServletResponse response) {
		UserService service = new UserService();
		request.setAttribute("users", service.getAllUsers());
	}
}