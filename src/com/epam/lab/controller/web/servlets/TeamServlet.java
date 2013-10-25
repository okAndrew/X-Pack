package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String TEAM_JSP = "WEB-INF/jsp/team.jsp";
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(TEAM_JSP);
		requestDispatcher.forward(request, response);
	}

}
