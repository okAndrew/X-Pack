package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userfiles;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;

public class AdminSimpleUserRequestHelperFile {

	private static AdminSimpleUserRequestHelperFile instance = null;
	private Map<String, AdminSimpleUserPageCommand> commands = new HashMap<String, AdminSimpleUserPageCommand>();
	private static Logger logger = Logger
			.getLogger(AdminSimpleUserRequestHelperFile.class);

	private AdminSimpleUserRequestHelperFile() {
		commands.put("deleteWithSendEmail",
				new AdminSimpleUserFilesDelCommand());
	}

	public AdminSimpleUserPageCommand parseCommand(HttpServletRequest request) {
		String action = request.getParameter("action");
		AdminSimpleUserPageCommand command = commands.get(action);
		if (command == null) {
			logger.error("Command in AdminLogsPage is not found");
		}
		return command;
	}

	public static AdminSimpleUserRequestHelperFile getInstance() {
		if (instance == null) {
			instance = new AdminSimpleUserRequestHelperFile();
		}
		return instance;
	}
}