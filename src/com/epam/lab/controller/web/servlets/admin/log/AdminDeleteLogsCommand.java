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
		if (request.getParameterValues("checkLog") == null) {
			request.setAttribute("message", "Please check logs!!!");
			page = "adminLogsPage";
			return page;
		} else {
			LogServiceImpl service = new LogServiceImpl();
			service.deleteLogs(request.getParameterValues("checkLog"));
			page = "adminLogsPage";
		}
		return page;
	}
}
