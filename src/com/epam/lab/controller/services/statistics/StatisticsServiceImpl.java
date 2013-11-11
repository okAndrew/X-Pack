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
		StatisticsDAOImpl statisticsDaoImpl = new StatisticsDAOImpl();
		return statisticsDaoImpl.getAllDownloadStatistic();
	}

	@Override
	public List<Statistics> getAllDownloadStatisticByUserId(long id) {
		StatisticsDAOImpl statisticsDaoImpl = new StatisticsDAOImpl();
		return statisticsDaoImpl.getAllDownloadStatisticByUserId(id);
	}

	public List<Statistics> getAllByUserId(long userid) {
		StatisticsDAOImpl sessStatistics = new StatisticsDAOImpl();
		return sessStatistics.getAllByUserId(userid);
	}

	@Override
	public List<Statistics> getAllUploadStatistic() {
		StatisticsDAOImpl sessStatistics = new StatisticsDAOImpl();
		return sessStatistics.getAllUploadStatistic();
	}

	@Override
	public List<Statistics> getAllUploadStatisticByUserId(long userId) {
		StatisticsDAOImpl sessStatistics = new StatisticsDAOImpl();
		return sessStatistics.getAllUploadStatisticByUserId(userId);
	}

	public double getVisitsPerDayByUserId(long userId) {
		StatisticsDAOImpl sessdaoimpl = new StatisticsDAOImpl();
		Statistics stat = sessdaoimpl.getVisitsPerDayByUserId(userId);
		return stat.getNumber();

	}

	public String getAvarageTimeSessionByUserId(long userId) {
		StatisticsDAOImpl sessdaoimpl = new StatisticsDAOImpl();
		Statistics result = sessdaoimpl.getAvarageTimeSessionByUserId(userId);
		TimeZone tz = TimeZone.getTimeZone("UTC");
	    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	    df.setTimeZone(tz);
		String time = df.format((long)(result.getNumber()*1000));
		return time;
	}
}