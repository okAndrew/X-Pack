package com.epam.lab.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;
import com.epam.lab.model.User;

@WebServlet("/EditUserLoginServlet")
public class EditUserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		String message = null;
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		UserService userService = new UserService();
		User user = null;
		
		if (login != null && email != null && login != "") {
			try {
				user = userService.getUserByEmail(email);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else {
			message = "Fields cannot be empty";
		}
		
		if (user!= null) {
			try {
				userService.updateUser((int)user.getId(), login, user.getEmail(), (int)user.getIdTariff(), user.getToken());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "User with such email is not found";
		}
		
		request.setAttribute("message", message);
		dispatcher.forward(request, response);
	}

}
