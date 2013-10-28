package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.RegistrationService;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.Validator;
import com.epam.lab.model.Role;
import com.epam.lab.model.User;

@WebServlet("/updateUser")
public class UpdateAdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateAdminUserServlet() {
		super();
	}

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
		if (userService.checkEmailById(userEmail, userId)) {
			if (userService.ckeckLoginById(userLogin, userId)) {
				if (Validator.USER_LOGIN.validate(userLogin)) {
					User user = userService.get(userId).setLogin(userLogin)
							.setEmail(userEmail).setIsActivated(activated)
							.setIsBanned(banned).setRole(role);

					userService.update(user);
					User user1 = userService.get(userId);
					request.setAttribute("user", user1);
				} else {
					request.setAttribute("message",
							"Login must be string without numbers");
				}
			} else {
				request.setAttribute("message", "Login already exists");
			}
		} else {
			request.setAttribute("message", "Email already exists");

		}
		dispatcher = request.getRequestDispatcher("adminUser?userid=" + userId);
		dispatcher.forward(request, response);
	}
}
