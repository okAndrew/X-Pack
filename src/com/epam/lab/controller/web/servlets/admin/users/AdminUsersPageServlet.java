package com.epam.lab.controller.web.servlets.admin.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.SelectService;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.User;

@WebServlet("/adminUsersPage")
public class AdminUsersPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USERS_PAGE_JSP = "WEB-INF/jsp/admin/users/adminUsersPage.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getUsers(request, response);
		getNamesTariffs(request, response);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USERS_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getUsers(request, response);
		if (request.getParameter("message") != null) {
			request.setAttribute("message", null);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_USERS_PAGE_JSP);
		dispatcher.forward(request, response);
	}

	private void getUsers(HttpServletRequest request,
			HttpServletResponse response) {
		UserServiceImpl service = new UserServiceImpl();
		SelectService<User> selectService = new SelectService<User>();
		List<User> users = selectService.getByParam(User.class,
				request.getParameter("page"), request.getParameter("count"),
				request.getParameter("orderby"), request.getParameter("sop"));
		request.setAttribute("users", users);
		request.setAttribute("usersCount", service.getCount());
	}

	private void getNamesTariffs(HttpServletRequest request,
			HttpServletResponse response){
		TariffServiseImpl service = new TariffServiseImpl();
		request.setAttribute("tariffsNames", service.getHashMapNames());
	}
}