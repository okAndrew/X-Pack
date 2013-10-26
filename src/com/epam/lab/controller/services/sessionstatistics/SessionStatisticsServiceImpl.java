package com.epam.lab.controller.services.sessionstatistics;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.sessionstatistics.SessionStatisticsDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.SessionStatistics;

public class SessionStatisticsServiceImpl extends
		AbstractServiceImpl<SessionStatistics> implements
		SessionStatisticsService {

	public SessionStatisticsServiceImpl() {
		super(new SessionStatisticsDAOImpl());
	}

	static Logger logger = Logger.getLogger(SessionStatisticsServiceImpl.class);

	public String toJson(List<SessionStatistics> sessionStatisticsList) {
		StringBuilder resultData = new StringBuilder();
		resultData.append("[[");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Iterator<SessionStatistics> iterator = sessionStatisticsList.iterator();
		while (iterator.hasNext()) {
			SessionStatistics statistics = iterator.next();
			StringBuilder pointSB = new StringBuilder();
			pointSB.append("[\"").append(sdf.format(statistics.getDay()))
					.append("\",").append(statistics.getCount()).append("]");
			resultData.append(pointSB.toString());
			if (iterator.hasNext()) {
				resultData.append(",");
			}
		}
		resultData.append("]]");
		return resultData.toString();
	}
}