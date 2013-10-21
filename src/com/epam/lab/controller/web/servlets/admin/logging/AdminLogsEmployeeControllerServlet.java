package com.epam.lab.controller.web.servlets.admin.logging;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminLogsEmployeeController")
public class AdminLogsEmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminLogsRequestHelper requestHelper = AdminLogsRequestHelper
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
		AdminLogsPageCommand command = requestHelper.parseCommand(request);
		// try{
		page = command.execute(request, response);
		// } catch() {
		//
		// }
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
