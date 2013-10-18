package com.epam.lab.controller.web.servlets.admin.users;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class AdminUsersRequestHelper {

	private static AdminUsersRequestHelper instance = null;
	private Map<String, AdminUsersPageCommand> commands = new HashMap<String, AdminUsersPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminUsersRequestHelper.class);

	private AdminUsersRequestHelper() {
		commands.put("sendEmailUsers", new AdminSendEmailCommand());
		commands.put("baned", new AdminBanedUsersCommand());
		commands.put("activated", new AdminActivateUsersCommand());
		commands.put("delete", new AdminDeleteUsersCommand());
		commands.put("add", new AdminAddUserCommand());
	}

	public AdminUsersPageCommand getCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminUsersPageCommand command = commands.get(action);
		if (command == null) {
			/*
	     * 
	     */
			logger.error("Command in AdminUsersPage is not found");
		}
		return command;
	}

	public static AdminUsersRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminUsersRequestHelper();
		}
		return instance;
	}
}