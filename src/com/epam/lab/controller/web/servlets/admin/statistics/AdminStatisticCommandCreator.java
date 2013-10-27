package com.epam.lab.controller.web.servlets.admin.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.lab.controller.web.servlets.admin.log.AdminLogsRequestHelper;

public class AdminStatisticCommandCreator {

	private static AdminStatisticCommandCreator instance = null;
	private Map<String, AdminStatisticPageCommand> commands = new HashMap<String, AdminStatisticPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminLogsRequestHelper.class);

	private AdminStatisticCommandCreator() {
		commands.put("users", new AdminStatisticUsersCommand());
		commands.put("files", new AdminStatisticFilesCommand());
		commands.put("server", new AdminStatisticServerCommand());
	}

	public AdminStatisticPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("page");
		AdminStatisticPageCommand command = commands.get(action);
		if (command == null) {
			logger.error("Command in AdminLogsPage is not found");
		}
		return command;
	}

	public static AdminStatisticCommandCreator getInstance() {
		if (instance == null) {
			instance = new AdminStatisticCommandCreator();
		}
		return instance;
	}
}