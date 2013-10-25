package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ERROR_JSP = "WEB-INF/jsp/errorSW.jsp";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ERROR_JSP);
		requestDispatcher.forward(request, response);
	}
}
