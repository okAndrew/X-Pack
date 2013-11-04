package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.statistics.traffichistory.TrafficHistoryServiceImpl;

public class AdminStatisticServerCommand implements AdminStatisticPageCommand{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		setDataDownlInResponse(request, response);
		setDataUploadInResponse(request, response);
		page = "WEB-INF/jsp/admin/statistics/adminStatisticsPage.jsp";
		return page;
	}

	private void setDataDownlInResponse(HttpServletRequest request,
			HttpServletResponse response){
		TrafficHistoryServiceImpl traffisService = new TrafficHistoryServiceImpl();
		request.setAttribute("downlLastDay", traffisService.getDownloadTrafficByLastDay());
		request.setAttribute("downlLastMounts", traffisService.getDownloadTrafficByLastMounth());
		request.setAttribute("downlLastWeek", traffisService.getDownloadTrafficByLastWeek());

	}
	
	private void setDataUploadInResponse(HttpServletRequest request,
			HttpServletResponse response){
		TrafficHistoryServiceImpl traffisService = new TrafficHistoryServiceImpl();
		request.setAttribute("uploadLastMounts", traffisService.getUploadTrafficByLastMounth());
		request.setAttribute("uploadLastWeek", traffisService.getUploadTrafficByLastWeek());
		request.setAttribute("uploadLastDay", traffisService.getUploadTrafficByLastDay());
	}
}
