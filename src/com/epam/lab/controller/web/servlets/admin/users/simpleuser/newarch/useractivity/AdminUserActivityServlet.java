package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.useractivity;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.epam.lab.controller.services.statistics.StatisticsServiceImpl;
import com.epam.lab.controller.services.statistics.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.SessionHistory;
import com.epam.lab.model.Statistics;

@WebServlet("/adminUserActivity")
public class AdminUserActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_USER_ACTIVITY = "WEB-INF/jsp/admin/users/simpleUser/adminUserActivity.jsp";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StatisticsServiceImpl serviceImpl = new StatisticsServiceImpl();
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("adminUserid");
		double visitsByUserId = serviceImpl.getVisitsPerDayByUserId(userId);
		String avarageSession = serviceImpl.getAvarageTimeSessionByUserId(userId);
		request.setAttribute("visitsByUserId", visitsByUserId);
		request.setAttribute("avarageSession", avarageSession);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USER_ACTIVITY);
		dispatcher.forward(request, response);
	}

}
