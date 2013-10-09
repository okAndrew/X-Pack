package com.epam.lab.view.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.AdminService;

@WebServlet("/signInAdmin")
public class SignInAdmin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SIGN_IN_ADMIN_JSP = "WEB-INF/jsp/admin/signInAdmin.jsp";
	private static final String ADMIN_HOME = "adminHome";
	private static final Logger logger = Logger.getLogger(SignInAdmin.class);

	public SignInAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(SIGN_IN_ADMIN_JSP);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		AdminService service = new AdminService();

		if (service.validate(login, password)) {
			HttpSession session = request.getSession();
			session.setAttribute(login, login);
			logger.info("Admin are entering with login...");
			dispatcher = request.getRequestDispatcher(ADMIN_HOME);
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message",
					"Please try again!!! Your login or password failed!!!");
			logger.info("Log in admin " + login + "failed");
			dispatcher = request.getRequestDispatcher(SIGN_IN_ADMIN_JSP);
			dispatcher.forward(request, response);
		}
	}
}