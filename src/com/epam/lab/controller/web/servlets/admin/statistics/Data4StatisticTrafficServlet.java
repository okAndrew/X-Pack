package com.epam.lab.controller.web.servlets.admin.statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.epam.lab.controller.services.sessionstatistics.SessionStatisticsServiceImpl;
import com.epam.lab.model.SessionStatistics;

@WebServlet("/Data4StatisticTraffic")
public class Data4StatisticTrafficServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionStatisticsServiceImpl statisticsServiceImpl = new SessionStatisticsServiceImpl();
		List<SessionStatistics> list = new ArrayList<SessionStatistics>();
		list = statisticsServiceImpl.getAllDownloadStatistic();
		String stringData = statisticsServiceImpl.toJson(list);
		response.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(stringData.toString());
		try {
			response.getWriter().write(stringData.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
