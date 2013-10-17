package com.epam.lab.controller.web.servlets.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.User;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		HttpSession session = request.getSession(false);
		
		String password = request.getParameter("password");
		User user = (User) session.getAttribute("user");
		
		UserService userService = new UserService();
		userService.changeUserPassword(user, password);
		
		requestDispatcher.forward(request, response);
	}

}
