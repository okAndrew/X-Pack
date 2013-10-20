package com.epam.lab.controller.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.codegen.LongCache;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.User;

@WebServlet("/CreatePaymentServlet")
public class CreatePaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "settings";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long id = Long.valueOf(request.getParameter("id"));
		long tariff = Long.valueOf(request.getParameter("tariff"));
		int result = new TariffServiseImpl().changeTariff(id, tariff);
		
		if (result == 1) {
			HttpSession session = request.getSession(false);
			User user = new UserServiceImpl().get(id);
			session.setAttribute("user", user);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		requestDispatcher.forward(request, response);
	}
}
