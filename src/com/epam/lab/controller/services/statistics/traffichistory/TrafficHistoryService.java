package com.epam.lab.controller.services.statistics.traffichistory;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.TrafficHistory;

public interface TrafficHistoryService extends AbstractService<TrafficHistory> {

	double getDownloadTrafficByLastMounth();

	double getDownloadTrafficByLastWeek();

	double getDownloadTrafficByLastDay();

	double getUploadTrafficByLastMounth();

	double getUploadTrafficByLastWeek();

	double getUploadTrafficByLastDay();

	double getDownloadTrafficUserByLastMounth(long userId);

	double getDownloadTrafficUserByLastWeek(long userId);

	double getDownloadTrafficUserByLastDay(long userId);

	double getUploadTrafficUserByLastMounth(long userId);

	double getUploadTrafficUserByLastWeek(long userId);

	double getUploadTrafficUserByLastDay(long userId);
}
