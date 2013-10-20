package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;

public class AdminDeleteTariffsCommand implements AdminTariffsPageCommand{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		String page = null;
		String[] checkTariffs = request.getParameterValues("checkTariff");
		TariffServiseImpl service = new TariffServiseImpl();
		message = service.deteteTariffs(checkTariffs);
		request.setAttribute("message", message);
		page = "adminTariffsPage";
		return page;
	}

}
