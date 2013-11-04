package com.epam.lab.controller.web.servlets.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.RegistrationService;

@WebServlet("/activation")
public class ActivationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_JSP = "homepage";
	static Logger logger = Logger.getLogger(ActivationServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGNIN_JSP);
		String token = request.getParameter("token");
		boolean activation = false;
		
		logger.debug("try to activate");
		
		activation = new RegistrationService().activateUser(token);
		
		logger.debug(activation);
		
		if (!activation) {
			request.setAttribute("message", "Failed_to_activate");
		}
		
		requestDispatcher.forward(request, response);
	}
}
