package com.epam.lab.controller.web.servlets.admin.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.SelectService;
import com.epam.lab.controller.services.log.LogServiceImpl;
import com.epam.lab.model.Log;
import com.epam.lab.model.User;

public class AdminGetAllLogsCommand implements AdminLogsPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
//		LogServiceImpl service = new LogServiceImpl();
		SelectService<Log> selectService = new SelectService<Log>();
		List<Log> logs = selectService.getByParam(Log.class, request.getParameter("page"), request.getParameter("count"), request.getParameter("orderby"), request.getParameter("sop"));
		request.setAttribute("logs", logs);
		page = "adminLogsPage";
		return page;
	}

}
