package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;

public class AdminEditTariffCommand implements AdminTariffsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String maxCapacity = request.getParameter("maxCapacity");
		String price = request.getParameter("price");
		String position = request.getParameter("position");
		String description = request.getParameter("description");
		TariffServiseImpl service = new TariffServiseImpl();
		String editTarMessage = service.updateTariff(id, name, maxCapacity, price, position,
				description);
		request.setAttribute("editTarMessage", editTarMessage);
		page = "adminTariffsPage";
		return page;
	}
}
