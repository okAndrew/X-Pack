package com.epam.lab.controller.web.servlets.admin.logging;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class AdminLogsRequestHelper {

	private static AdminLogsRequestHelper instance = null;
	private Map<String, AdminLogsPageCommand> commands = new HashMap<String, AdminLogsPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminLogsRequestHelper.class);

	private AdminLogsRequestHelper() {
		commands.put("all", new AdminGetAllLogsCommand());
		commands.put("error", new AdminGetErrorLogsCommand());
		commands.put("warning", new AdminGetWarningLogsCommand());
		commands.put("info", new AdminGetInfoLogsCommand());
		commands.put("debug", new AdminGetDebugLogsCommand());
	}

	public AdminLogsPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminLogsPageCommand command = commands.get(action);
		if (command == null) {
			/*
	     * 
	     */
			logger.error("Command in AdminLogsPage is not found");
			logger.debug("qwertyuiodcvj");
		}
		return command;
	}

	public static AdminLogsRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminLogsRequestHelper();
		}
		return instance;
	}
}