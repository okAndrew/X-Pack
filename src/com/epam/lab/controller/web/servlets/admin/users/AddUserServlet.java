package com.epam.lab.controller.web.servlets.admin.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.RegistrationService;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADD_USER_MODAL_JSP = "WEB-INF/jsp/admin/users/addUserModalPage.jsp";
	private static final String HOME_USERS_PAGE = "adminUsersPage";
	private static Logger logger = Logger.getLogger(AddUserServlet.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String errorMessage = null;
		RegistrationService service = new RegistrationService();
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		errorMessage = service.regUser(login, email, password);
		if (errorMessage == null) {
			dispatcher = request.getRequestDispatcher(HOME_USERS_PAGE);
			logger.info("user by email" + email + "add to databases");
		} else {
			request.setAttribute("message", errorMessage);
			dispatcher = request.getRequestDispatcher(ADD_USER_MODAL_JSP);
			logger.error("user by email" + email + "had error to create acount");
		}
		dispatcher.forward(request, response);
	}
}
