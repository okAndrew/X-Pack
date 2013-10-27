package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Role;
import com.epam.lab.model.User;

@WebServlet("/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_USER_JSP = "WEB-INF/jsp/admin/users/simpleUser/adminUser.jsp";

	public UpdateAdminUserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		int userId = Integer.parseInt(request.getParameter("userIdHolder"));
		String userLogin = request.getParameter("userLogin");// check
		String userEmail = request.getParameter("userEmail");// check
		boolean activated = Boolean.parseBoolean(request
				.getParameter("userActivation"));
		Role role = Role.findByName(request.getParameter("userRole"));

		UserServiceImpl userService = new UserServiceImpl();
		if (userService.checkEmailById(userEmail,userId)) {
			if (userService.ckeckLoginById(userLogin,userId)) {
				User user = userService.get(userId).setLogin(userLogin)
						.setEmail(userEmail).setIsActivated(activated)
						.setRole(role);

				userService.update(user);
				User user1 = userService.get(userId);
				request.setAttribute("user", user1);
				request.getRequestDispatcher(ADMIN_USER_JSP).forward(request,
						response);
			} else {
				request.setAttribute("message", "Login already exists");
				dispatcher = request.getRequestDispatcher("adminUser?userid="+userId);
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Email already exists");
			dispatcher = request.getRequestDispatcher("adminUser?userid="+userId);
			dispatcher.forward(request, response);
		}

	}
}
