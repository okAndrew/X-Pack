package com.epam.lab.controller.web.servlets.admin.users.simpleuser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.lab.controller.web.servlets.admin.users.AdminActivateUsersCommand;
import com.epam.lab.controller.web.servlets.admin.users.AdminAddUserCommand;
import com.epam.lab.controller.web.servlets.admin.users.AdminBanedUsersCommand;
import com.epam.lab.controller.web.servlets.admin.users.AdminDeleteUsersCommand;
import com.epam.lab.controller.web.servlets.admin.users.AdminSendEmailCommand;

public class AdminSimpleUsersRequestHelper {

	private static AdminSimpleUsersRequestHelper instance = null;
	private Map<String, AdminSimpleUserPageCommand> commands = new HashMap<String, AdminSimpleUserPageCommand>();

	private AdminSimpleUsersRequestHelper() {
		commands.put("adminUserInfo", new AdminUserInfoCommand());
		commands.put("adminUserFiles", new AdminUserFilesCommand());
		commands.put("adminUserPayments", new AdminUserPaymentsCommand());
		commands.put("adminUserActivity", new AdminUserActivityCommand());
		commands.put("adminUserTraffic", new AdminUserTrafficCommand());
	}

	public AdminSimpleUserPageCommand getCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminSimpleUserPageCommand command = commands.get(action);
		return command;
	}

	public static AdminSimpleUsersRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminSimpleUsersRequestHelper();
		}
		return instance;
	}
}