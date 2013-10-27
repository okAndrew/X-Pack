package com.epam.lab.controller.services.sessionstatistics;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.SessionStatistics;

public interface SessionStatisticsService extends AbstractService<SessionStatistics> {

	List<SessionStatistics> getAllDownloadStatistic();
	
	List<SessionStatistics> getAllDownloadStatisticByUserId(long id);
}
