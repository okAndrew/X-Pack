package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.useractivity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.lab.controller.services.statistics.StatisticsServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;

public class AdminUserActivityCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		setData(request, response, userId);
		page = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
		return page;
	}

	private void setData(HttpServletRequest request,
			HttpServletResponse response, long userId) {
		StatisticsServiceImpl serviceImpl = new StatisticsServiceImpl();
		double visitsByUserId = serviceImpl.getVisitsPerDayByUserId(userId);
		String avarageSession = serviceImpl.getAvarageTimeSessionByUserId(userId);
		request.setAttribute("visitsByUserId", visitsByUserId);
		request.setAttribute("avarageSession", avarageSession);
	}

}
