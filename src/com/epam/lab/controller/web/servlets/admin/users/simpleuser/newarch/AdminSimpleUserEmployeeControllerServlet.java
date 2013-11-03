package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/userEmployeeController")
public class AdminSimpleUserEmployeeControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AdminSimpleUserRequestHelper requestHelper = AdminSimpleUserRequestHelper.getInstance();
	private static Logger logger = Logger.getLogger(AdminSimpleUserEmployeeControllerServlet.class);
	
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
			logger.error("Command not found in adminSimpleUserPage");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("adminUser");
			dispatcher.forward(request, response);
		} else {
			page = command.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
}
