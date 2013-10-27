package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.SendRestorePassMail;

@WebServlet("/SendRestorePass")
public class SendRestorePass extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGNIN_JSP = "signin";
	private static final String RESTORE_JSP = "WEB-INF/jsp/sendrestorepass.jsp";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = null;
		String email = request.getParameter("email");
		String message = null;
		
		if (email != null) {
			message = new SendRestorePassMail().send(email);
			
			if (message == null) {
				requestDispatcher = request.getRequestDispatcher(SIGNIN_JSP);
			} else {
				requestDispatcher = request.getRequestDispatcher(RESTORE_JSP);
			}
		} else {
			requestDispatcher = request.getRequestDispatcher(RESTORE_JSP);
		}
		
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}
		
	
}
