package com.epam.lab.controller.web.servlets.admin.users;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class AdminUsersRequestHelper {

	private static AdminUsersRequestHelper instance = null;
	private Map<String, AdminUsersPageCommand> commands = new HashMap<String, AdminUsersPageCommand>();

	private AdminUsersRequestHelper() {
		commands.put("sendEmailUsers", new AdminSendEmailCommand());
		commands.put("baned", new AdminBanedUsersCommand());
		commands.put("activated", new AdminActivateUsersCommand());
		commands.put("delete", new AdminDeleteUsersCommand());
		commands.put("add", new AdminAddUserCommand());
		commands.put("restore", new AdminRestoreUsersCommand());
	}

	public AdminUsersPageCommand getCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminUsersPageCommand command = commands.get(action);
		return command;
	}

	public static AdminUsersRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminUsersRequestHelper();
		}
		return instance;
	}
}