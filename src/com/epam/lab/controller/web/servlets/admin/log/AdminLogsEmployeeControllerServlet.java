package com.epam.lab.controller.web.servlets.admin.log;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/adminLogsEmployeeController")
public class AdminLogsEmployeeControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String ADMIN_LOGS_PAGE = "adminLogsPage";
	private static Logger logger = Logger
			.getLogger(AdminLogsEmployeeControllerServlet.class);
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
		AdminLogsPageCommand command = requestHelper.parseCommand(request);;
		if (command == null) {
			logger.error("Command in AdminLogsPage not found");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ADMIN_LOGS_PAGE);
			dispatcher.forward(request, response);
		} else {
			page = command.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
}
