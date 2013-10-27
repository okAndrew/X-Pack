package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.traffichistory.TrafficHistoryServiceImpl;

public class AdminStatisticServerCommand implements AdminStatisticPageCommand{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		setDataDownlInResponse(request, response);
		page = "adminStatisticPage";
		return page;
	}

	private void setDataDownlInResponse(HttpServletRequest request,
			HttpServletResponse response){
		TrafficHistoryServiceImpl traffisService = new TrafficHistoryServiceImpl();
		request.setAttribute("downlLastMounts", traffisService.getDownloadTrafficByLastMounth());
		request.setAttribute("downlLastWeek", traffisService.getDownloadTrafficByLastWeek());
		request.setAttribute("downlLastDay", traffisService.getDownloadTrafficByLastDay());
	}
}
