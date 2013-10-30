package com.epam.lab.controller.web.servlets.admin.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.SelectService;
import com.epam.lab.controller.services.log.LogServiceImpl;
import com.epam.lab.model.Log;

@WebServlet("/adminLogsPage")
public class AdminLoggingPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String ADMIN_LOGGING_PAGE_JSP = "WEB-INF/jsp/admin/log/adminLogsPage.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		selectLogs(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_LOGGING_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		selectLogs(request, response);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_LOGGING_PAGE_JSP);
		dispatcher.forward(request, response);

	}

	private void selectLogs(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("logsCount", new LogServiceImpl().getCount());
		SelectService<Log> selectService = new SelectService<Log>();
		List<Log> logs = selectService.getByParam(Log.class,
				request.getParameter("page"), request.getParameter("count"),
				request.getParameter("orderby"), request.getParameter("sop"));
		request.setAttribute("logs", logs);
	}
}