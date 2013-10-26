package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;

public class AdminDeleteTariffsCommand implements AdminTariffsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		if (request.getParameterValues("checkTariff") == null) {
			request.setAttribute("message", "Please check tariffs!!!");
			page = "adminTariffsPage";
			return page;
		} else {
			TariffServiseImpl service = new TariffServiseImpl();
			service.deteteTariffs(request.getParameterValues("checkTariff"));
			page = "adminTariffsPage";
		}
		return page;
	}

}
