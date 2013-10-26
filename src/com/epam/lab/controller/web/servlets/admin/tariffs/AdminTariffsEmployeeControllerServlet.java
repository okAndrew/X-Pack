package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/employeeControllerTariffs")
public class AdminTariffsEmployeeControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(AdminTariffsEmployeeControllerServlet.class);
	private static String ADMIN_TARIFFS_PAGE = "adminTariffsPage";
	private AdminTariffsRequestHelper requestHelper = AdminTariffsRequestHelper
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
		AdminTariffsPageCommand command;
		command = requestHelper.parseCommand(request);
		if (command == null) {
			logger.error("Command not found in AdminTariffsCommands");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher(ADMIN_TARIFFS_PAGE);
			dispatcher.forward(request, response);
		} else {
			page = command.execute(request, response);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}
}