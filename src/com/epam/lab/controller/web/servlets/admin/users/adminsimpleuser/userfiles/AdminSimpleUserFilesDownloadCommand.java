package com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.userfiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.lab.controller.web.servlets.admin.users.adminsimpleuser.AdminSimpleUserPageCommand;

public class AdminSimpleUserFilesDownloadCommand implements
		AdminSimpleUserPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = "download";
		return page;
	}

}
