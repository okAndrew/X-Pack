package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminStatisticsPage")
public class AdminStatisticsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_STATISTICS_JSP = "WEB-INF/jsp/admin/statistics/adminStatisticsPage.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_STATISTICS_JSP);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_STATISTICS_JSP);
		dispatcher.forward(request, response);
	}

}
