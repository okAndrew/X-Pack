package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.lab.controller.services.locale.LocaleServiceImpl;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String HOMEPAGE_JSP = "WEB-INF/jsp/index.jsp";

	public HomePageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LocaleServiceImpl locImpl = new LocaleServiceImpl();
		if (session.getAttribute("sessLocale").toString().equals("")) {
			session.setAttribute("sessLocale", request.getLocale());
			session.setAttribute("currentLanguage", locImpl.getByLocale(session
					.getAttribute("sessLocale").toString()));
		}
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(HOMEPAGE_JSP);
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(HOMEPAGE_JSP);
		requestDispatcher.forward(request, response);
	}
}