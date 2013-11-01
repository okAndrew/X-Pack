package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.statistics.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.AdminSimpleUserPageCommand;

public class AdminUserActivityCommand implements AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("adminUserid");
		setData(request, response, userId);
		page = "adminUserActivity";
		return page;
	}

	private void setData(HttpServletRequest request,
			HttpServletResponse response, long userId) {
		SessionHistoryServiceImpl historyServiceImpl = new SessionHistoryServiceImpl();
		long visitsByUserId = historyServiceImpl
				.getVisitsPerDayByUserId(userId);
		Timestamp avarageSession = historyServiceImpl
				.getAvarageTimeSessionByUserId(userId);
		request.setAttribute("visitsByUserId", visitsByUserId);
		request.setAttribute("avarageSession", avarageSession);
	}

}
