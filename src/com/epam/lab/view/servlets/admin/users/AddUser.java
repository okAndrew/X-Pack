package com.epam.lab.view.servlets.admin.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;

@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADD_USER_MODAL_JSP = "WEB-INF/jsp/admin/users/addUserModalPage.jsp";
	private static final String HOME_USERS_PAGE = "WEB-INF/jsp/admin/users/addUserModalPage.jsp";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(HOME_USERS_PAGE);
		UserService service = new UserService();
		String login = request.getParameter("loginUser");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		service.insertUser(login, email, password);
		dispatcher.forward(request, response);
	}
}
