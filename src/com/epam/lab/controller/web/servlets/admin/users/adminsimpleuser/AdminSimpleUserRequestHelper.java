package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.useractivity.AdminUserActivityCommand;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userfiles.AdminSimpleUserFilesCommand;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userinfo.AdminSimpleUserInfoCommand;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userinfo.AdminSimpleUserUpdateCommand;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userpayments.AdminUserPaymentsByDateCommand;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userpayments.AdminUserPaymentsCommand;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.usertraffic.AdminUserTrafficCommand;


public class AdminSimpleUserRequestHelper {

	private static AdminSimpleUserRequestHelper instance = null;
	private Map<String, AdminSimpleUserPageCommand> commands = new HashMap<String, AdminSimpleUserPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminSimpleUserRequestHelper.class);

	private AdminSimpleUserRequestHelper() {
		commands.put("adminUserInfo", new AdminSimpleUserInfoCommand());
		commands.put("adminUserFiles", new AdminSimpleUserFilesCommand());
		commands.put("adminUserPayments", new AdminUserPaymentsCommand());
		commands.put("adminUserActivity", new AdminUserActivityCommand());
		commands.put("adminUserTraffic", new AdminUserTrafficCommand());
		commands.put("paymentsByDate", new AdminUserPaymentsByDateCommand());
		commands.put("updateUser", new AdminSimpleUserUpdateCommand());
	}

	public AdminSimpleUserPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("page");
		AdminSimpleUserPageCommand command = commands.get(action);
		if (command == null) {
			logger.error("Command in AdminLogsPage is not found");
		}
		return command;
	}

	public static AdminSimpleUserRequestHelper getInstance() {
		if (instance == null) {
			instance = new AdminSimpleUserRequestHelper();
		}
		return instance;
	}
}