package com.epam.lab.controller.services.traffichistory;

import java.util.List;

import com.epam.lab.controller.dao.traffichistory.TrafficHistoryDAOImpl;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.TrafficHistory;

public class TrafficHistoryServiceImpl implements TrafficHistoryService {

	private TrafficHistoryDAOImpl traDaoImpl = new TrafficHistoryDAOImpl();
	private UserFileServiceImpl fileServiceImpl = new UserFileServiceImpl();

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

	@Override
	public double getDownloadTrafficByLastMounth() {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfMonth(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfMonth(TimeStampManager.getCurrentTime()));
		return (traffic.getSize() / 1024 / 1024);
	}

	@Override
	public double getDownloadTrafficByLastWeek() {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()));
		return (traffic.getSize() / 1024 / 1024);
	}

	@Override
	public double getDownloadTrafficByLastDay() {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfDay(TimeStampManager
						.getCurrentTime()), TimeStampManager.getCurrentTime());
		return (traffic.getSize() / 1024 / 1024);
	}

	@Override
	public double getUploadTrafficByLastMounth() {
		Long size = fileServiceImpl.getDownloadTrafficByDates(TimeStampManager
				.getStartOfMonth(TimeStampManager.getCurrentTime()),
				TimeStampManager.getEndOfMonth(TimeStampManager
						.getCurrentTime()));
		return (size / 1024 / 1024);
	}

	@Override
	public double getUploadTrafficByLastWeek() {
		Long size = fileServiceImpl
				.getDownloadTrafficByDates(TimeStampManager
						.getStartOfWeek(TimeStampManager.getCurrentTime()),
						TimeStampManager.getEndOfWeek(TimeStampManager
								.getCurrentTime()));
		return (size / 1024 / 1024);
	}

	@Override
	public double getUploadTrafficByLastDay() {
		Long size = fileServiceImpl.getDownloadTrafficByDates(TimeStampManager
				.getStartOfDay(TimeStampManager.getCurrentTime()),
				TimeStampManager.getCurrentTime());
		return (size / 1024 / 1024);
	}
}