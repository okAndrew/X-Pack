package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.PaymentService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.Payment;
import com.epam.lab.model.User;

@WebServlet("/adminUserInfo")
public class AdminUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
	private static final Logger logger = Logger
			.getLogger(AdminUserInfoServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		HttpSession session = request.getSession(false);

		long userId = (long) session.getAttribute("userid");

		User user = us.getUserById(userId);

		request.setAttribute("user", user);

		request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	}

}
