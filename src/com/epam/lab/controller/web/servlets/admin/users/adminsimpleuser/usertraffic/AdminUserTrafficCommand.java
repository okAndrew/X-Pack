package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.usertraffic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.statistics.traffichistory.TrafficHistoryServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;

public class AdminUserTrafficCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession();
		long userId = (long) session.getAttribute("adminUserid");
		setDataDownload(request, response, userId);
		setDataUpload(request, response, userId);
		page = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
		return page;

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
