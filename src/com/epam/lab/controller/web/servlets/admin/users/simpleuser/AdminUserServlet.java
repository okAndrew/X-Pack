package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

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
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		long userId = Long.parseLong(request.getParameter("userid"));
		session.setAttribute("adminUserid", userId);

		UserServiceImpl userService = new UserServiceImpl();
		User user = userService.get(userId);

		request.setAttribute("user", user);

		request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	}

}
