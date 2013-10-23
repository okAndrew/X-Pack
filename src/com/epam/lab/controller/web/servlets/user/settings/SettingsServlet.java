package com.epam.lab.controller.web.servlets.user.settings;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.Tariff;
import com.epam.lab.model.User;

@WebServlet("/settings")
public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SETTINGS_JSP = "WEB-INF/jsp/user/settings.jsp";
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		initialize(request, response);
		requestDispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(SETTINGS_JSP);
		initialize(request, response);
		requestDispatcher.forward(request, response);
	}
	
	private void initialize(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		UserServiceImpl userService = new UserServiceImpl();
		TariffServiseImpl tariffService = new TariffServiseImpl();
		Object id = null;
		Tariff tariff = null;
		User user = null;
		
		if (session != null) {
			id = session.getAttribute("userid");
			user  = userService.get(Long.valueOf(id.toString()));
			tariff = tariffService.get(user.getIdTariff());
		}
		
		request.setAttribute("tariff", tariff);
		request.setAttribute("user", user);
	}
	
}
