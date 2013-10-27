package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminUserTraffic")
public class AdminUserTrafficServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_USER_TRAFFIC = "WEB-INF/jsp/admin/users/simpleUser/adminUserTraffic.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USER_TRAFFIC);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USER_TRAFFIC);
		dispatcher.forward(request, response);

	}

}
