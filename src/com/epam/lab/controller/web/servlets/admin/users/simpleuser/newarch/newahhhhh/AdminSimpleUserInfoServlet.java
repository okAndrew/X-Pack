package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.newahhhhh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.User;

@WebServlet("/adminUser")
public class AdminSimpleUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";

	private AdminSimpleUserRequestHelper commandHelper = AdminSimpleUserRequestHelper
			.getInstance();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = ADMIN_USER_JSP;
		long userId = 0;
		HttpSession session = request.getSession();
		if (request.getParameter("userid") == null) {
			userId = (long) session.getAttribute("adminUserid");
		} else {
			userId = Long.parseLong(request.getParameter("userid"));
			session.setAttribute("adminUserid", userId);
		}
		UserServiceImpl userService = new UserServiceImpl();
		User user = userService.get(userId);
		request.setAttribute("adminUser", user);
		AdminSimpleUserPageCommand command = commandHelper.parseCommand(request);
		if (command != null) {
			page = command.execute(request, response);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	// protected void doGet(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// long userId = 0;
	// HttpSession session = request.getSession();
	// if (request.getParameter("userid") == null) {
	// userId = (long) session.getAttribute("adminUserid");
	// } else {
	// userId = Long.parseLong(request.getParameter("userid"));
	// session.setAttribute("adminUserid", userId);
	// }
	// UserServiceImpl userService = new UserServiceImpl();
	// User user = userService.get(userId);
	// request.setAttribute("adminUser", user);
	// request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	// }
	//
	// protected void doPost(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// doGet(request, response);
	// }

}
