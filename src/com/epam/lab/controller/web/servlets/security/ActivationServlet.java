package com.epam.lab.controller.web.servlets.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.RegistrationService;

@WebServlet("/activation")
public class ActivationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_JSP = "signin";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGNIN_JSP);
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		
		int res = new RegistrationService().activateUser(email, token);
		
		requestDispatcher.forward(request, response);
	}

}
