package com.epam.lab.view.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.User;

@WebServlet("/settings")
public class Settings extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "WEB-INF/jsp/user/settings.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = new UserService();
		Long id = (Long) (request.getSession().getAttribute("userid"));
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		requestDispatcher.forward(request, response);
	}

}
