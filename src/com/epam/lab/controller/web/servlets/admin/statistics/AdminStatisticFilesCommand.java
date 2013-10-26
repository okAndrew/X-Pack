package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.services.file.TypesService;
import com.epam.lab.controller.utils.DiskSpaceUtil;

public class AdminStatisticFilesCommand implements AdminStatisticPageCommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getFreeSpace(request, response);
		getTotalSpace(request, response);
		getTypeFilesGroup(request, response);
		String page = "WEB-INF/jsp/admin/statistics/adminStatisticsPage.jsp";
		return page;
	}

	private void getFreeSpace(HttpServletRequest request,
			HttpServletResponse response) {
		DiskSpaceUtil service = new DiskSpaceUtil();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		session.setAttribute("freeSpace", service.getFreeSpace());
		request.setAttribute("freeSpace", service.getFreeSpace());

	}

	private void getTotalSpace(HttpServletRequest request,
			HttpServletResponse response) {
		DiskSpaceUtil service = new DiskSpaceUtil();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		session.setAttribute("totalSpace", service.getTotalSpace());
		request.setAttribute("totalSpace", service.getTotalSpace());
	}

	private void getTypeFilesGroup(HttpServletRequest request,
			HttpServletResponse response) {
		TypesService service = new TypesService();
		request.setAttribute("types", service.getTypesFiles());
	}
}
