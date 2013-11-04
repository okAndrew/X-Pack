package com.epam.lab.controller.web.servlets;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/SendChangePasswordMail")
public class SendChangePasswordMail extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_JSP = "signin";
	private static final String RESTOREPASS_JSP = "WEB-INF/jsp/restorepass.jsp";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGNIN_JSP);
		
		String email = request.getParameter("email");
		UserServiceImpl userService = new UserServiceImpl();
		String msg = userService.SendMailRestorePass(email);
		
		request.setAttribute("message", msg);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(RESTOREPASS_JSP);
		requestDispatcher.forward(request, response);
	}

}
