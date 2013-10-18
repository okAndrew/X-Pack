package com.epam.lab.controller.web.servlets.admin;

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
import com.epam.lab.model.Admin;

@WebServlet("/signInAdmin")
public class SignInAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SIGN_IN_ADMIN_JSP = "WEB-INF/jsp/admin/signInAdmin.jsp";
	private static final String ADMIN_HOME = "adminPage";
	private static final Logger logger = Logger
			.getLogger(SignInAdminServlet.class);

	public SignInAdminServlet() {
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
		Admin admin = service.getAdminByLogin(login);

		if (service.validate(login, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			session.setAttribute("adminid", admin.getId());

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

/*
 * protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  logger.debug("LoginPage.doPost()");
  User user = null;
  RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_JSP);
  String login = request.getParameter("login");
  String password = request.getParameter("password");
  UserDaoImpl userDao = new UserDaoImpl();
  user = userDao.findByLogginPassword(login, password);
  if (user != null){
   HttpSession session = request.getSession();
   session.setAttribute("type", user.getType().getName());
   session.setAttribute("readerId", user.getIdUser());
   logger.debug("authenticated");
   response.sendRedirect(user.getType() + "Page");
  } else {
   request.setAttribute("message", "Your login or password failed!!!! Please try again!!!");
   dispatcher.forward(request, response);
   return;
 }
}*/
 