package com.epam.lab.view.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.RegistrationService;
import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.User;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNUP_JSP = "WEB-INF/jsp/signup.jsp";
	private static final String HOME_JSP = "WEB-INF/index.jsp";
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		RegistrationService regService = new RegistrationService();
		User user = regService.checkEmail(email);
		
		if (user == null) {
			UserService userService = new UserService();
			userService.insertUser(email, password);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", email);
			dispatcher = request.getRequestDispatcher(HOME_JSP);
		} else {
			request.setAttribute("message", "User with such email is registered.");
			dispatcher = request.getRequestDispatcher(SIGNUP_JSP);
		}
		
		dispatcher.forward(request, response);
	}
}
