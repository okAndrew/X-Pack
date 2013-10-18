package com.epam.lab.controller.web.servlets.user.settings;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.UserService;
import com.epam.lab.controller.services.security.Validator;
import com.epam.lab.model.User;

@WebServlet("/EditUserLoginServlet")
public class EditUserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(SETTINGS_JSP);
		String message = null;
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		User user = null;

		if (login != null && email != null && Validator.USER_LOGIN.validate(login)) {
			user = new UserService().changeUserLogin(email, login);
			
			if (user != null) {
				HttpSession session = request.getSession(false);
				session.setAttribute("user", user);
			} else {
				message = "User with such email is not found";
			}
		} else {
			message = "Error. Check fields.";
		}

		

		request.setAttribute("editLoginError", message);
		dispatcher.forward(request, response);
	}

}