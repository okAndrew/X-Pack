package com.epam.lab.controller.web.servlets.admin.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.UserService;

@WebServlet("/deleteUsers")
public class DeleteUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_USERS_PAGE = "adminUsersPage";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String message = null;
		String[] chekedUsers = request.getParameterValues("checkUser");
		UserService service = new UserService();
		message = service.deleteUsers(chekedUsers);
		request.setAttribute("message", message);
		dispatcher = request.getRequestDispatcher(ADMIN_USERS_PAGE);
		dispatcher.forward(request, response);
	}
}
