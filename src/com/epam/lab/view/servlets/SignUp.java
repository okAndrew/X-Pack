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
import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.User;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNUP_JSP = "WEB-INF/jsp/signup.jsp";
	private static final String SIGNIN_JSP = "WEB-INF/jsp/signin.jsp";
	static Logger logger = Logger.getLogger(SignUp.class.getSimpleName());

	public SignUp() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGNUP_JSP);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		RegistrationService regService = new RegistrationService();
		User user = regService.checkEmail(email);
		
		if (user == null) {
			UserService userService = new UserService();
			userService.insertUser(login, email, password);
			
			dispatcher = request.getRequestDispatcher(SIGNIN_JSP);
		} else {
			request.setAttribute("message", "User with such email is registered.");
			dispatcher = request.getRequestDispatcher(SIGNUP_JSP);
		}
		
		dispatcher.forward(request, response);
	}
}
