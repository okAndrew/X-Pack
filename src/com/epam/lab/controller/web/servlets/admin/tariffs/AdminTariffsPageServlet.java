package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.SelectService;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.model.Tariff;

@WebServlet("/adminTariffsPage")
public class AdminTariffsPageServlet extends HttpServlet {

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
		TariffServiseImpl servise = new TariffServiseImpl();
		SelectService<Tariff> selectService = new SelectService<Tariff>();
		List<Tariff> tariffs = selectService.getByParam(Tariff.class,
				request.getParameter("page"), request.getParameter("count"),
				request.getParameter("orderby"), request.getParameter("sop"));
		request.setAttribute("tariffs", tariffs);
		request.setAttribute("tariffsCount", servise.getCount());
	}
}
