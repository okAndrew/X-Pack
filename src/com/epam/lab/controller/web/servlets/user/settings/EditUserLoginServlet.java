package com.epam.lab.controller.web.servlets.user.settings;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.user.UserServiceImpl;

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
		String login = request.getParameter("login");
		String email = request.getParameter("email");

		boolean edit = new UserServiceImpl().editLogin(email, login);

		if (edit) {
			HttpSession session = request.getSession(false);
			session.setAttribute("userLogin", login);
		} else {
			message = "Error";
		}
		
		request.setAttribute("editLoginError", message);
		dispatcher.forward(request, response);
	}

}