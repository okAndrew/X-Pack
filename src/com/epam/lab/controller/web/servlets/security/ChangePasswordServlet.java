package com.epam.lab.controller.web.servlets.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.Validator;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";
	static Logger logger = Logger.getLogger(ChangePasswordServlet.class);
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		HttpSession session = request.getSession(false);
		UserServiceImpl userService = new UserServiceImpl();
		Long userId = null;
		String msg = null;
		
		String oldPassword = request.getParameter("old_pass");
		String password = request.getParameter("password");
		String passwordRetype = request.getParameter("password_retype");
		
		try {
			userId = Long.valueOf(session.getAttribute("userid").toString());
			
			if (Validator.USER_PASSWORD.validate(oldPassword) && Validator.USER_PASSWORD.validate(password) && Validator.USER_PASSWORD.validate(passwordRetype)) {
				msg = userService.changeUserPassword(userId, oldPassword, password, passwordRetype);
			} else {
				msg = "password_is_incorrect";
			}
		} catch (NumberFormatException e) {
			logger.error(e);
			msg = "Internal_error._Reload_page";
		}
		
		request.setAttribute("message", msg);
		requestDispatcher.forward(request, response);
	}

}
