package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;
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
		int userId = Integer.parseInt(request.getParameter("userIdHolder"));
		String userLogin = request.getParameter("userLogin");// check
		String userEmail = request.getParameter("userEmail");// check
		boolean activated = Boolean.parseBoolean(request
				.getParameter("userActivation"));
		Role role = Role.findByName(request.getParameter("userRole"));

		UserService userService = new UserService();
		User user = userService.getUserById(userId).setLogin(userLogin)
				.setEmail(userEmail).setIsActivated(activated).setRole(role);

		userService.updateUser(user);
		User user1 = userService.getUserById(userId);

		request.setAttribute("user", user1);

		request.getRequestDispatcher(ADMIN_USER_JSP).forward(request, response);
	}

}
