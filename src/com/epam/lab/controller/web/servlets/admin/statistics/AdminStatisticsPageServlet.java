package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminStatisticsPage")
public class AdminStatisticsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_STATISTICS_PAGE = "WEB-INF/jsp/admin/statistics/adminStatisticsPage.jsp";

	private AdminStatisticCommandCreator commandCreator = AdminStatisticCommandCreator
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
		String page = ADMIN_STATISTICS_PAGE;
		AdminStatisticPageCommand command = commandCreator
				.parseCommand(request);
		if (command != null) {
			page = command.execute(request, response);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
