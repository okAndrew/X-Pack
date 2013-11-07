package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userinfo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Role;

@WebServlet("/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		int userId = Integer.parseInt(request.getParameter("userIdHolder"));
		String userLogin = request.getParameter("userLogin");
		String userEmail = request.getParameter("userEmail");
		boolean activated = Boolean.parseBoolean(request
				.getParameter("userActivation"));
		boolean banned = Boolean.parseBoolean(request
				.getParameter("userBanned"));
		Role role = Role.findByName(request.getParameter("userRole"));
		UserServiceImpl userService = new UserServiceImpl();
		String errmessage = userService.checkUpdate(userEmail, userId,
				userLogin, activated, banned, role);
		request.setAttribute("message", errmessage);
		dispatcher = request.getRequestDispatcher("adminUser?userid=" + userId);
		dispatcher.forward(request, response);
	}
}
