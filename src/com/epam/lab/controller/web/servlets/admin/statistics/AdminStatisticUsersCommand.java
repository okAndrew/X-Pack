package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.controller.web.listeners.UserOnlineListener;
import com.epam.lab.model.SessionHistory;
import com.epam.lab.model.User;

public class AdminStatisticUsersCommand implements AdminStatisticPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getAllVisitorsByLastDay(request, response);
		getAllVisitorsByLastWeek(request, response);
		getAllVisitorsByLastMonth(request, response);
		getLoggedUsers(request, response);
		getAllUsers(request, response);
		getOnlineUsers(request, response);
		String page = "WEB-INF/jsp/admin/statistics/adminStatisticsPage.jsp";
		return page;
	}

	private void getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		UserServiceImpl service = new UserServiceImpl();
		List<User> list = new ArrayList<User>();
		list = service.getAll();
		long countAllUsers = list.size();
		request.setAttribute("countAllUsers", countAllUsers);

	}

	private void getOnlineUsers(HttpServletRequest request,
			HttpServletResponse response) {
		int countUsers = UserOnlineListener.getActiveSessionNumber();
		request.setAttribute("countUsers", countUsers);

	}

	private void getLoggedUsers(HttpServletRequest request,
			HttpServletResponse response) {
		int countUsersLogged = UserOnlineListener
				.getActiveSessionNumberLogged();
		request.setAttribute("countUsersLogged", countUsersLogged);

	}

	private void getAllVisitorsByLastDay(HttpServletRequest request,
			HttpServletResponse response) {
		SessionHistoryServiceImpl historyServiceImpl = new SessionHistoryServiceImpl();
		List<SessionHistory> list = historyServiceImpl.getAllVisitorsByDate(
				TimeStampManager.getStartOfDay(TimeStampManager
						.getCurrentTime()), TimeStampManager.getCurrentTime());
		request.setAttribute("visitorsByDay", list.size());
		List<SessionHistory> listLogged = historyServiceImpl
				.getLoggedVisitorsByDate(TimeStampManager
						.getStartOfDay(TimeStampManager.getCurrentTime()),
						TimeStampManager.getCurrentTime());
		request.setAttribute("loggedVisitorsByDay", listLogged.size());

	}

	private void getAllVisitorsByLastWeek(HttpServletRequest request,
			HttpServletResponse response) {
		SessionHistoryServiceImpl historyServiceImpl = new SessionHistoryServiceImpl();
		List<SessionHistory> list = historyServiceImpl.getAllVisitorsByDate(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()));
		request.setAttribute("visitorsByWeek", list.size());
		List<SessionHistory> listLogged = historyServiceImpl
				.getLoggedVisitorsByDate(TimeStampManager
						.getStartOfWeek(TimeStampManager.getCurrentTime()),
						TimeStampManager.getEndOfWeek(TimeStampManager
								.getCurrentTime()));
		request.setAttribute("loggedVisitorsByWeek", listLogged.size());

	}

	private void getAllVisitorsByLastMonth(HttpServletRequest request,
			HttpServletResponse response) {
		SessionHistoryServiceImpl historyServiceImpl = new SessionHistoryServiceImpl();
		List<SessionHistory> list = historyServiceImpl.getAllVisitorsByDate(
				TimeStampManager.getStartOfMonth(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfMonth(TimeStampManager.getCurrentTime()));
		request.setAttribute("visitorsByMonth", list.size());
		List<SessionHistory> listLogged = historyServiceImpl
				.getLoggedVisitorsByDate(TimeStampManager
						.getStartOfMonth(TimeStampManager.getCurrentTime()),
						TimeStampManager.getEndOfMonth(TimeStampManager
								.getCurrentTime()));
		request.setAttribute("loggedVisitorsByMonth", listLogged.size());

	}
}
