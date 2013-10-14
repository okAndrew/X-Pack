package com.epam.lab.controller.web.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(name = "locale", urlPatterns = { "/locale" })
public class LocaleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String HOMEPAGE_JSP = "WEB-INF/index.jsp";
	private static Logger logger = Logger.getLogger(LocaleServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.setAttribute("sessLocale", request.getParameter("language"));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(HOMEPAGE_JSP);
		requestDispatcher.forward(request, response);
	}
}
