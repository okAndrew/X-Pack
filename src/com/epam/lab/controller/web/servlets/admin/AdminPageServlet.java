package com.epam.lab.controller.web.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.web.listeners.UserOnlineListener;
import com.epam.lab.model.User;

@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_PAGE_JSP = "WEB-INF/jsp/admin/adminPage.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UserServiceImpl service = new UserServiceImpl();
		List<User> list = new ArrayList<User>();
		list = service.getAll();
		long countAllUsers = list.size();
		if (session == null) {
			request.getRequestDispatcher(ADMIN_PAGE_JSP).forward(request,
					response);
		} else {
//			UserOnlineListener counter = (UserOnlineListener) session
//					.getAttribute("counter");
//			int countUsers = counter.getActiveSessionNumber();
//			request.setAttribute("countUsers", countUsers);
//			request.setAttribute("countAllUsers", countAllUsers);
			request.getRequestDispatcher(ADMIN_PAGE_JSP).forward(request,
					response);
		}
	}
}
/*
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { doPost(request, response); }
 * 
 * // delete some of methods protected void doPost(HttpServletRequest request,
 * HttpServletResponse response) throws ServletException, IOException {
 * HttpSession session = request.getSession(false); UserService service = new
 * UserService(); List<User> list = new ArrayList<User>(); list =
 * service.getAllUsers(); long countAllUsers = list.size(); if (session == null)
 * { request.getRequestDispatcher(ADMIN_PAGE_JSP).forward(request, response); }
 * else { UserOnlineListener counter = (UserOnlineListener) session
 * .getAttribute("counter"); int countUsers = counter.getActiveSessionNumber();
 * request.setAttribute("countUsers", countUsers);
 * request.setAttribute("countAllUsers", countAllUsers);
 * request.getRequestDispatcher(ADMIN_PAGE_JSP).forward(request, response); } }
 */

