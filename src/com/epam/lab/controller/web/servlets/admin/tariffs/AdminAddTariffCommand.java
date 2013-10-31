package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;

public class AdminAddTariffCommand implements AdminTariffsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		TariffServiseImpl servise = new TariffServiseImpl();
		String name = request.getParameter("name");
		String maxCapacity = request.getParameter("maxCapacity");
		String price = request.getParameter("price");
		String position = request.getParameter("position");
		String description = request.getParameter("description");
		String addTarMessage = servise.addTariff(name, maxCapacity, price, position,
				description);
		request.setAttribute("addTarMessage", addTarMessage);
		page = "adminTariffsPage";
		return page;
	}

}