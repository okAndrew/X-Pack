package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.web.listeners.UserOnlineListener;
import com.epam.lab.model.User;

public class AdminStatisticUsersCommand implements AdminStatisticPageCommand{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession(false);
		UserServiceImpl service = new UserServiceImpl();
		List<User> list = new ArrayList<User>();
		list = service.getAll();
		long countAllUsers = list.size();
		if (session == null) {
			page = "signin";
		} else {
			int countUsersLogged = UserOnlineListener.getActiveSessionNumberLogged();
			int countUsers = UserOnlineListener.getActiveSessionNumber();
			request.setAttribute("countUsers", countUsers);
			request.setAttribute("countUsersLogged", countUsersLogged);
			request.setAttribute("countAllUsers", countAllUsers);
			page = "WEB-INF/jsp/admin/statistics/users.jsp";
		}
		return page;
	}
}
