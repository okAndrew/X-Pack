package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class AdminTariffsRequestHelper {
	
	private static AdminTariffsRequestHelper instance = null;
	private Map<String, AdminTariffsPageCommand> commands = new HashMap<String, AdminTariffsPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminTariffsRequestHelper.class);

	private AdminTariffsRequestHelper() {
		commands.put("addTariff", new AdminAddTariffCommand());
		commands.put("isDelete", new AdminDeleteTariffCommand());
	}

	public AdminTariffsPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminTariffsPageCommand command = commands.get(action);
		if (command == null) {
			/*
	     * 
	     */
			logger.error("Command in AdminTariffsPage is not found");
		}
		return command;
	}

	public static AdminTariffsRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminTariffsRequestHelper();
		}
		return instance;
	}

}
