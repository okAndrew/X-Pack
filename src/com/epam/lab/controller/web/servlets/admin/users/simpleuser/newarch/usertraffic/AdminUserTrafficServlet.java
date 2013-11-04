package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.usertraffic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.statistics.traffichistory.TrafficHistoryServiceImpl;

@WebServlet("/adminUserTraffic")
public class AdminUserTrafficServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_USER_TRAFFIC = "WEB-INF/jsp/admin/users/simpleUser/adminUserTraffic.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("adminUserid");
		setDataDownload(request, response, userId);
		setDataUpload(request, response, userId);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USER_TRAFFIC);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USER_TRAFFIC);
		dispatcher.forward(request, response);

	}

	private void setDataDownload(HttpServletRequest request,
			HttpServletResponse response, long userId) {
		TrafficHistoryServiceImpl traffisService = new TrafficHistoryServiceImpl();
		request.setAttribute("downlUserLastDay",
				traffisService.getDownloadTrafficUserByLastDay(userId));
		request.setAttribute("downlUserLastWeek",
				traffisService.getDownloadTrafficUserByLastWeek(userId));
		request.setAttribute("downlUserLastMonth",
				traffisService.getDownloadTrafficUserByLastMounth(userId));
	}

	private void setDataUpload(HttpServletRequest request,
			HttpServletResponse response, long userId) {
		TrafficHistoryServiceImpl traffisService = new TrafficHistoryServiceImpl();
		request.setAttribute("uploadUserLastDay",
				traffisService.getUploadTrafficUserByLastDay(userId));
		request.setAttribute("uploadUserLastWeek",
				traffisService.getUploadTrafficUserByLastWeek(userId));
		request.setAttribute("uploadUserLastMonth",
				traffisService.getUploadTrafficUserByLastMounth(userId));

	}
}
