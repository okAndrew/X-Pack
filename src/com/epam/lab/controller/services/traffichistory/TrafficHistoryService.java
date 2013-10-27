package com.epam.lab.controller.services.traffichistory;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.TrafficHistory;

public interface TrafficHistoryService extends AbstractService<TrafficHistory> {

	double getDownloadTrafficByLastMounth();

	double getDownloadTrafficByLastWeek();

	double getDownloadTrafficByLastDay();

	double getUploadTrafficByLastMounth();

	double getUploadTrafficByLastWeek();

	double getUploadTrafficByLastDay();
}
