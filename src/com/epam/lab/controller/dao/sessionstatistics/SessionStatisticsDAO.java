package com.epam.lab.controller.dao.sessionstatistics;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.SessionStatistics;

public interface SessionStatisticsDAO extends GenericDAO<SessionStatistics> {

	List<SessionStatistics> getAllDownloadStatistic();

	List<SessionStatistics> getAllDownloadStatisticByUserId(long id);

	List<SessionStatistics> getAllByUserId(long userid);

}
