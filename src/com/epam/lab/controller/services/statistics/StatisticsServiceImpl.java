package com.epam.lab.controller.services.statistics;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.statistics.StatisticsDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.Statistics;

public class StatisticsServiceImpl extends AbstractServiceImpl<Statistics>
		implements StatisticsService {
	private StatisticsDAOImpl statisticsDAOImpl = (StatisticsDAOImpl) dao;
	public StatisticsServiceImpl() {
		super(new StatisticsDAOImpl());
	}

	static Logger logger = Logger.getLogger(StatisticsServiceImpl.class);

	public String toJson(List<Statistics> sessionStatisticsList) {
		StringBuilder resultData = new StringBuilder();
		resultData.append("[[");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Iterator<Statistics> iterator = sessionStatisticsList.iterator();
		while (iterator.hasNext()) {
			Statistics statistics = iterator.next();
			StringBuilder pointSB = new StringBuilder();
			pointSB.append("[\"").append(sdf.format(statistics.getDay()))
					.append("\",").append(statistics.getNumber()).append("]");
			resultData.append(pointSB.toString());
			if (iterator.hasNext()) {
				resultData.append(",");
			}
		}
		resultData.append("]]");
		return resultData.toString();
	}

	@Override
	public List<Statistics> getAllDownloadStatistic() {
		return statisticsDAOImpl.getAllDownloadStatistic();
	}

	@Override
	public List<Statistics> getAllDownloadStatisticByUserId(long id) {
		return statisticsDAOImpl.getAllDownloadStatisticByUserId(id);
	}

	public List<Statistics> getAllByUserId(long userid) {
		return statisticsDAOImpl.getAllByUserId(userid);
	}

	@Override
	public List<Statistics> getAllUploadStatistic() {
		return statisticsDAOImpl.getAllUploadStatistic();
	}

	@Override
	public List<Statistics> getAllUploadStatisticByUserId(long userId) {
		return statisticsDAOImpl.getAllUploadStatisticByUserId(userId);
	}

	public double getVisitsPerDayByUserId(long userId) {
		Statistics stat = statisticsDAOImpl.getVisitsPerDayByUserId(userId);
		return stat.getNumber();

	}

	public String getAvarageTimeSessionByUserId(long userId) {
		Statistics result = statisticsDAOImpl.getAvarageTimeSessionByUserId(userId);
		TimeZone tz = TimeZone.getTimeZone("UTC");
	    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	    df.setTimeZone(tz);
		String time = df.format((long)(result.getNumber()*1000));
		return time;
	}
}