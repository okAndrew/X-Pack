package com.epam.lab.controller.services.statistics;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Statistics;

public interface StatisticsService extends AbstractService<Statistics> {

	List<Statistics> getAllDownloadStatistic();
	
	List<Statistics> getAllDownloadStatisticByUserId(long userId);

	List<Statistics> getAllByUserId(long userid);
	
	List<Statistics> getAllUploadStatistic();
	
	List<Statistics> getAllUploadStatisticByUserId(long userId);

}
