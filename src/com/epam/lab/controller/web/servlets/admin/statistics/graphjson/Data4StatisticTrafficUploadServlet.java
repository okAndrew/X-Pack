package com.epam.lab.controller.web.servlets.admin.statistics.graphjson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.epam.lab.controller.services.statistics.StatisticsServiceImpl;
import com.epam.lab.model.Statistics;

@WebServlet("/Data4StatisticTrafficUpload")
public class Data4StatisticTrafficUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StatisticsServiceImpl statisticsServiceImpl = new StatisticsServiceImpl();
		List<Statistics> list = new ArrayList<Statistics>();
		list = statisticsServiceImpl.getAllUploadStatistic();
		String stringData = statisticsServiceImpl.toJson(list);
		response.setContentType(MediaType.APPLICATION_JSON);
		try {
			response.getWriter().write(stringData.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
