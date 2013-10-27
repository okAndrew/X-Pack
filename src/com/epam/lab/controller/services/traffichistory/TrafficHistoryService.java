package com.epam.lab.controller.services.traffichistory;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.TrafficHistory;

public interface TrafficHistoryService extends AbstractService<TrafficHistory>{

	int getDownloadTrafficByLastMounth();
	
	int getDownloadTrafficByLastWeek();
	
	int getDownloadTrafficByLastDay();
}
