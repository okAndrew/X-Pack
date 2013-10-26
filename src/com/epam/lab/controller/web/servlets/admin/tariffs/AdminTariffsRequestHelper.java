package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class AdminTariffsRequestHelper {

	private static AdminTariffsRequestHelper instance = null;
	private Map<String, AdminTariffsPageCommand> commands = new HashMap<String, AdminTariffsPageCommand>();

	private AdminTariffsRequestHelper() {
		commands.put("addTariff", new AdminAddTariffCommand());
		commands.put("isDelete", new AdminDeleteTariffsCommand());
		commands.put("isActivate", new AdminActivateTariffsCommand());
		commands.put("editTariff", new AdminEditTariffCommand());
	}

	public AdminTariffsPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminTariffsPageCommand command = commands.get(action);
		return command;
	}

	public static AdminTariffsRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminTariffsRequestHelper();
		}
		return instance;
	}
}