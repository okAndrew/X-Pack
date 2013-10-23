package com.epam.lab.controller.web.servlets.user.settings;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/EditEmailServlet")
public class EditEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		String message = null;
		String oldEmail = request.getParameter("oldEmail");
		String newEmail = request.getParameter("newEmail");

		boolean edit = new UserServiceImpl().tryEditEmail(oldEmail, newEmail);

		if (!edit) {
			message = "Error";
		}

		request.setAttribute("editEmailError", message);
		dispatcher.forward(request, response);
	}

}
