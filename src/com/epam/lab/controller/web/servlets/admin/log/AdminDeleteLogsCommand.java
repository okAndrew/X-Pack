package com.epam.lab.controller.web.servlets.admin.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.log.LogServiceImpl;

public class AdminDeleteLogsCommand implements AdminLogsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		String message = null;
		String[] checkLogs = request.getParameterValues("checkLog");
		LogServiceImpl service = new LogServiceImpl();
		message = service.deleteLogs(checkLogs);
		request.setAttribute("message", message);
		page = "adminLogsPage";
		return page;
	}

}
