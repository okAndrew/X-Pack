package com.epam.lab.controller.web.servlets.admin.log;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class AdminLogsRequestHelper {

	private static AdminLogsRequestHelper instance = null;
	private Map<String, AdminLogsPageCommand> commands = new HashMap<String, AdminLogsPageCommand>();

	private AdminLogsRequestHelper() {
		commands.put("all", new AdminGetAllLogsCommand());
		commands.put("error", new AdminGetErrorLogsCommand());
		commands.put("warning", new AdminGetWarningLogsCommand());
		commands.put("info", new AdminGetInfoLogsCommand());
		commands.put("debug", new AdminGetDebugLogsCommand());
		commands.put("delete", new AdminDeleteLogsCommand());
		commands.put("clear", new AdminClearLogsCommand());
	}

	public AdminLogsPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminLogsPageCommand command = commands.get(action);
		return command;
	}

	public static AdminLogsRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminLogsRequestHelper();
		}
		return instance;
	}
}