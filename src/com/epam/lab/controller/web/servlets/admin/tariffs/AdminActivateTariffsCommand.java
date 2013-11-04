package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.tariff.TariffServiseImpl;

public class AdminActivateTariffsCommand implements AdminTariffsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		if (request.getParameterValues("checkTariff") == null) {
			page = "adminTariffsPage";
			request.setAttribute("message", "Please_check_tariffs");
			return page;
		} else {
			TariffServiseImpl service = new TariffServiseImpl();
			service.activateTariffs(request.getParameterValues("checkTariff"));
			page = "adminTariffsPage";
		}
		return page;
	}

}
