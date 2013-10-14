package com.epam.lab.view.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.User;

@WebServlet("/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";
	private static final Logger logger = Logger
			.getLogger(UpdateAdminUserServlet.class);

	public UpdateAdminUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userIdHolder"));
		String userLogin = request.getParameter("userLogin");// check
		String userEmail = request.getParameter("userEmail");// check
		int userIdTariff = Integer.parseInt(request
				.getParameter("userIdTariff"));
		String userToken = request.getParameter("userToken");// check

		UserService.updateUser(userId, userLogin, userEmail, userIdTariff,
				userToken);
		User user = UserService.getUserById(userId);

		request.setAttribute("user", user);

		request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	}

}
