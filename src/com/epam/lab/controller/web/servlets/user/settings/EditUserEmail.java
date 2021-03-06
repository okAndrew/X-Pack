package com.epam.lab.controller.web.servlets.user.settings;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.user.UserServiceImpl;

@WebServlet("/edituseremail")
public class EditUserEmail extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldEmail = request.getParameter("oldEmail");
		String newEmail = request.getParameter("newEmail");
		String token = request.getParameter("token");
		
		UserServiceImpl userService = new UserServiceImpl();
		userService.editUserEmail(oldEmail, newEmail, token);
		
		response.sendRedirect(SETTINGS_JSP);
	}

}
