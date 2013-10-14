package com.epam.lab.view.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.TariffServise;

@WebServlet("/addTariff")
public class AddTariffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADD_TARIFF_MODAL_PAGE = "WEB-INF/jsp/admin/tariffs/addTariffModalPage.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		TariffServise servise = new TariffServise();
		String name = request.getParameter("name");
		int maxCapacity = Integer.parseInt(request.getParameter("maxCapacity"));
		servise.insert(name, maxCapacity);
		dispatcher = request.getRequestDispatcher(ADD_TARIFF_MODAL_PAGE);
		dispatcher.forward(request, response);
	}

}
