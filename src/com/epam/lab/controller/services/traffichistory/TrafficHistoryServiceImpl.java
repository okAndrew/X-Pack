package com.epam.lab.controller.services.traffichistory;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.traffichistory.TrafficHistoryDAOImpl;
import com.epam.lab.model.TrafficHistory;

public class TrafficHistoryServiceImpl implements TrafficHistoryService{

	private TrafficHistoryDAOImpl traDaoImpl = new TrafficHistoryDAOImpl();
	private static Logger logger = Logger.getLogger(TrafficHistoryServiceImpl.class);
	
	@Override
	public TrafficHistory get(long id) {
		return traDaoImpl.get(id);
	}

	@Override
	public List<TrafficHistory> getAll() {
		return traDaoImpl.getAll();
	}

	@Override
	public int insert(TrafficHistory object) {
		return traDaoImpl.insert(object);
	}

	@Override
	public int update(TrafficHistory object) {
		return traDaoImpl.update(object);
	}

	@Override
	public int delete(long id) {
		return traDaoImpl.delete(id);
	}

}
