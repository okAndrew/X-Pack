package com.epam.lab.controller.dao.statistics;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.SessionHistory;
import com.epam.lab.model.Statistics;

public interface StatisticsDAO extends GenericDAO<Statistics> {

	List<Statistics> getAllDownloadStatistic();

	List<Statistics> getAllDownloadStatisticByUserId(long userId);

	List<Statistics> getAllUploadStatistic();

	List<Statistics> getAllUploadStatisticByUserId(long userId);
	
	List<Statistics> getAllByUserId(long userid);

	Statistics getVisitsPerDayByUserId(long userId);

	Statistics getAvarageTimeSessionByUserId(long userId);

}
