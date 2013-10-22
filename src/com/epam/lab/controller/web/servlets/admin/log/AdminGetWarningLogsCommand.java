package com.epam.lab.controller.web.servlets.admin.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.log.LogServiceImpl;

public class AdminGetWarningLogsCommand implements AdminLogsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		LogServiceImpl service = new LogServiceImpl();
		request.setAttribute("logs", service.getWarningLogs());
		page = "WEB-INF/jsp/admin/log/logsList.jsp";
		return page;
	}

}