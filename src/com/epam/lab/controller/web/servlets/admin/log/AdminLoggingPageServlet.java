package com.epam.lab.controller.web.servlets.admin.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminLogsPage")
public class AdminLoggingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_LOGGING_PAGE_JSP = "WEB-INF/jsp/admin/log/adminLogsPage.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_LOGGING_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_LOGGING_PAGE_JSP);
		dispatcher.forward(request, response);
	}
}
