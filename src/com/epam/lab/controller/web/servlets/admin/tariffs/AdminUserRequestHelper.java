package com.epam.lab.controller.web.servlets.admin.tariffs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.lab.controller.web.servlets.admin.users.simpleuser.AdminUserDeleteFilesCommand;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.AdminUserPageCommand;

public class AdminUserRequestHelper {

	private static AdminUserRequestHelper instance = null;
	private Map<String, AdminUserPageCommand> commands = new HashMap<String, AdminUserPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminUserRequestHelper.class);

	private AdminUserRequestHelper() {
		commands.put("delete", new AdminUserDeleteFilesCommand());
		
	}

	public AdminUserPageCommand getCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminUserPageCommand command = commands.get(action);
		if (command == null) {
			/*
	     * 
	     */
			logger.error("Command in AdminUsersPage is not found");
		}
		return command;
	}

	public static AdminUserRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminUserRequestHelper();
		}
		return instance;
	}
}
