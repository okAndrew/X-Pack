package com.epam.lab.controller.services.sessionstatistics;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

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

	public JSONObject toJson(List<SessionStatistics> sessionStatisticsList) {
		JSONObject jsonOb = new JSONObject();
		try {
			for (SessionStatistics sessionStatistics : sessionStatisticsList) {
				jsonOb.put(sessionStatistics.getDay().toString(),
						sessionStatistics.getCount());
			}

		} catch (JSONException e) {
			logger.error(e);
		}
		return jsonOb;
	}
}