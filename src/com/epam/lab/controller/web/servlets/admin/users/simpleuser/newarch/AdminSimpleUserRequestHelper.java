package com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.useractivity.AdminUserActivityCommand;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles.AdminSimpleUserFilesCommand;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userfiles.AdminSimpleUserFilesDelCommand;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userinfo.AdminSimpleUserInfoCommand;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.userpayments.AdminUserPaymentsCommand;
import com.epam.lab.controller.web.servlets.admin.users.simpleuser.newarch.usertraffic.AdminUserTrafficCommand;

public class AdminSimpleUserRequestHelper {

	private static AdminSimpleUserRequestHelper instance = null;
	private Map<String, AdminSimpleUserPageCommand> commands = new HashMap<String, AdminSimpleUserPageCommand>();

	private AdminSimpleUserRequestHelper() {
		commands.put("adminUserInfo", new AdminSimpleUserInfoCommand());
		commands.put("adminUserFiles", new AdminSimpleUserFilesCommand());
		commands.put("delete", new AdminSimpleUserFilesDelCommand());
		commands.put("adminUserPayments", new AdminUserPaymentsCommand());
		commands.put("adminUserActivity", new AdminUserActivityCommand());
		commands.put("adminUserTraffic", new AdminUserTrafficCommand());
	}

	public AdminSimpleUserPageCommand getCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminSimpleUserPageCommand command = commands.get(action);
		return command;
	}

	public static AdminSimpleUserRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminSimpleUserRequestHelper();
		}
		return instance;
	}
}