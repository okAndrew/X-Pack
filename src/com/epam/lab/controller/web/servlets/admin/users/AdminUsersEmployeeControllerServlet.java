package com.epam.lab.controller.web.servlets.admin.users;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employeeController")
public class AdminUsersEmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADD_USER_MODAL = "addUser";
	private static String DELETE_USERS = "deleteUsers";
	private static String BANED_USERS = "banedUsers";
	private static String ACTIVATED_USERS = "activatedUsers";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("add")){
			RequestDispatcher dispatcher = request.getRequestDispatcher(ADD_USER_MODAL);
			dispatcher.forward(request, response);
		} else if (action.equals("delete")){
			RequestDispatcher dispatcher = request.getRequestDispatcher(DELETE_USERS);
			dispatcher.forward(request, response);
		} else if (action.equals("baned")){
			RequestDispatcher dispatcher = request.getRequestDispatcher(BANED_USERS);
			dispatcher.forward(request, response);
		} else if (action.equals("activated")){
			RequestDispatcher dispatcher = request.getRequestDispatcher(ACTIVATED_USERS);
			dispatcher.forward(request, response);
		}
		
		
	}
}
