package com.epam.lab.controller.web.servlets.user.settings;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.RegistrationService;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.User;

@WebServlet("/EditUserLoginServlet")
public class EditUserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(SETTINGS_JSP);
		String message = null;
		HttpSession session = request.getSession(true);
		String login = request.getParameter("login");
		String email = request.getParameter("email");

		UserServiceImpl userservice = new UserServiceImpl();
		RegistrationService registrationService = new RegistrationService();
		User userIndb = registrationService.checkLogin(login);
		boolean userHasLogin = userservice.ckeckLoginById(login,
				(long) session.getAttribute("userid"));
		if (userHasLogin) {
			if (userIndb == null) {
				boolean edit = new UserServiceImpl().editLogin(email, login);
				if (edit) {
					session.setAttribute("userLogin", login);
				} else {
					message = "Error";
				}
			} else {
				message = "You_already_have_that_login";
			}
		} else {
			message = "User_with_this_login_is_already_registered";
		}

		request.setAttribute("editLoginError", message);
		dispatcher.forward(request, response);
	}

}