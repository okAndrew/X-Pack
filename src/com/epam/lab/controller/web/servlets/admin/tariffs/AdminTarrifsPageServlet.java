package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.TariffServise;

@WebServlet("/adminTarrifsPage")
public class AdminTarrifsPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ADMIN_TARIFFS_PAGE = "WEB-INF/jsp/admin/tariffs/adminTariffsPage.jsp";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		getAllTariffs(request, response);
		dispatcher = request.getRequestDispatcher(ADMIN_TARIFFS_PAGE);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		getAllTariffs(request, response);
		dispatcher = request.getRequestDispatcher(ADMIN_TARIFFS_PAGE);
		dispatcher.forward(request, response);
	}

	private void getAllTariffs(HttpServletRequest request,
			HttpServletResponse response) {
		TariffServise servise = new TariffServise();
		request.setAttribute("tariffs", servise.getAllTariffs());
	}
}
