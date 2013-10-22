package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.TypesService;
import com.epam.lab.controller.utils.DiskSpaceUtil;

public class AdminStatisticFilesCommand implements AdminStatisticPageCommand{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		getFreeSpace(request, response);
		getTotalSpace(request, response);
		getTypeFilesGroup(request, response);
		page = "WEB-INF/jsp/admin/statistics/files.jsp";
		return page;
	}

	private void getFreeSpace(HttpServletRequest request,
			HttpServletResponse response) {
		DiskSpaceUtil service = new DiskSpaceUtil();
		request.setAttribute("freeSpace", service.getFreeSpace());

	}

	private void getTotalSpace(HttpServletRequest request,
			HttpServletResponse response) {
		DiskSpaceUtil service = new DiskSpaceUtil();
		request.setAttribute("totalSpace", service.getTotalSpace());
	}

	private void getTypeFilesGroup(HttpServletRequest request,
			HttpServletResponse response) {
		TypesService service = new TypesService();
		request.setAttribute("types", service.getTypesFiles());
	}
}