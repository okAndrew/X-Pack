package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.tariff.TariffServise;
import com.epam.lab.controller.services.tariff.TariffServiseImpl;
import com.epam.lab.model.Tariff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class AdminEditTariffContentServlet
 */
@WebServlet("/getEditTariffContent")
public class AdminEditTariffContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("id");
		Long idTariff = Long.parseLong(parameter);
		TariffServise tariffServise = new TariffServiseImpl();
		Tariff tariff = tariffServise.get(idTariff);
		if (tariff != null) {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(tariff);
			response.getWriter().write(json);
		}
	}
}
