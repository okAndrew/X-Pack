package com.epam.lab.controller.dao.traffichistory;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.TrafficHistory;

public interface TrafficHistoryDAO extends GenericDAO<TrafficHistory> {

	TrafficHistory getDownloadTrafficByDates(Timestamp dateStart,
			Timestamp dateEnd);

}
