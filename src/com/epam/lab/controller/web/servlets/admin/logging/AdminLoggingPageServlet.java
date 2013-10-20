package com.epam.lab.controller.web.servlets.admin.logging;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.logger.LogServiceImpl;

@WebServlet("/adminLoggingPage")
public class AdminLoggingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String ADMIN_LOGGING_PAGE_JSP = "WEB-INF/jsp/admin/logging/adminLoggingPage.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getLogs(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_LOGGING_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getLogs(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_LOGGING_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	private void getLogs(HttpServletRequest request, HttpServletResponse response){
		LogServiceImpl service = new LogServiceImpl();
		request.setAttribute("logs", service.getAll());
	}
}
