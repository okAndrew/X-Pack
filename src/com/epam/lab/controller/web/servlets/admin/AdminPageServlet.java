package com.epam.lab.controller.web.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN_PAGE_JSP = "WEB-INF/jsp/admin/adminPage.jsp";

	public AdminPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_PAGE_JSP);
		dispatcher.forward(request, response);
	}
// delete some of methods
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher(ADMIN_PAGE_JSP);
		dispatcher.forward(request, response);
	}

}