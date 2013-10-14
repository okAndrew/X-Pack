package com.epam.lab.controller.web.servlets.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;
import com.epam.lab.controller.services.security.EmailSecurityCode;
import com.epam.lab.controller.services.security.SendMail;
import com.epam.lab.model.User;

// rename or delete this class!!!!!!!!!!!!!!
@WebServlet("/EmailSecretCode")
public class EmailSecretCodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");

		UserService userService = new UserService();
		User user = userService.getUserByEmail(email);

		String code = EmailSecurityCode.getCode(user);

		System.out.println(user);

		SendMail.send(email, "Edit email", code
				+ "\nPaste this code into form to change your email address.");
	}

}
