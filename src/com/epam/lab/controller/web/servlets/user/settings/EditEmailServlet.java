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

@WebServlet("/EditEmailServlet")
public class EditEmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(SETTINGS_JSP);
		String message = null;
		HttpSession session = request.getSession(true);
		String oldEmail = request.getParameter("oldEmail");
		String newEmail = request.getParameter("newEmail");

		UserServiceImpl userservice = new UserServiceImpl();
		RegistrationService registrationService = new RegistrationService();
		User user = registrationService.checkEmail(newEmail);
		boolean userIndb = userservice.checkEmailById(newEmail,
				(long) session.getAttribute("userid"));
		if (userIndb) {
			if (user == null) {
				boolean edit = new UserServiceImpl().tryEditEmail(oldEmail,
						newEmail);
				if (!edit) {
					message = "Error";
				}
			} else {
				message = "You_already_have_that_email";
			}
		} else {
			message = "User_with_this_email_is_already_registered";
		}

		request.setAttribute("editEmailError", message);
		dispatcher.forward(request, response);
	}

}
