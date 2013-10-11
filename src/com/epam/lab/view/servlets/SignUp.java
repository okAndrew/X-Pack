package com.epam.lab.view.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.RegistrationService;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNUP_JSP = "WEB-INF/jsp/signup.jsp";
	private static final String SIGNIN_JSP = "signin";
	static Logger logger = Logger.getLogger(SignUp.class);

	public SignUp() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(SIGNUP_JSP);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String validError = null;
		
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		validError = RegistrationService.regUser(login, email, password);
		
		if (validError == null) {
			dispatcher = request.getRequestDispatcher(SIGNIN_JSP);
		} else {
			request.setAttribute("message", validError);
			dispatcher = request.getRequestDispatcher(SIGNUP_JSP);
		}

		dispatcher.forward(request, response);
	}
}
