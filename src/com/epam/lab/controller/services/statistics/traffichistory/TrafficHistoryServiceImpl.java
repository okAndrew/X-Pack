package com.epam.lab.controller.services.statistics.traffichistory;

import com.epam.lab.controller.dao.traffichistory.TrafficHistoryDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.TrafficHistory;

public class TrafficHistoryServiceImpl extends
		AbstractServiceImpl<TrafficHistory> implements TrafficHistoryService {
	private TrafficHistoryDAOImpl trafficHistoryDAOImpl = (TrafficHistoryDAOImpl) dao;

	public TrafficHistoryServiceImpl() {
		super(new TrafficHistoryDAOImpl());
	}
	private UserFileServiceImpl fileServiceImpl = new UserFileServiceImpl();

	@Override
	public double getDownloadTrafficByLastMounth() {
		TrafficHistory traffic = trafficHistoryDAOImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfMonth(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfMonth(TimeStampManager.getCurrentTime()));
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficByLastWeek() {
		TrafficHistory traffic = trafficHistoryDAOImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()));
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficByLastDay() {
		TrafficHistory traffic = trafficHistoryDAOImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfDay(TimeStampManager
						.getCurrentTime()), TimeStampManager.getCurrentTime());
		return traffic.getSize();
	}

	@Override
	public double getUploadTrafficByLastMounth() {
		Long size = fileServiceImpl.getUploadTrafficByDates(TimeStampManager
				.getStartOfMonth(TimeStampManager.getCurrentTime()),
				TimeStampManager.getEndOfMonth(TimeStampManager
						.getCurrentTime()));
		return size;
	}

	@Override
	public double getUploadTrafficByLastWeek() {
		Long size = fileServiceImpl
				.getUploadTrafficByDates(TimeStampManager
						.getStartOfWeek(TimeStampManager.getCurrentTime()),
						TimeStampManager.getEndOfWeek(TimeStampManager
								.getCurrentTime()));
		return size;
	}

	@Override
	public double getUploadTrafficByLastDay() {
		Long size = fileServiceImpl.getUploadTrafficByDates(TimeStampManager
				.getStartOfDay(TimeStampManager.getCurrentTime()),
				TimeStampManager.getCurrentTime());
		return size;
	}

	@Override
	public double getDownloadTrafficUserByLastMounth(long userId) {
		TrafficHistory traffic = trafficHistoryDAOImpl.getDownloadTrafficUserByDates(
				TimeStampManager.getStartOfMonth(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfMonth(TimeStampManager.getCurrentTime()),
				userId);
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficUserByLastWeek(long userId) {
		TrafficHistory traffic = trafficHistoryDAOImpl.getDownloadTrafficUserByDates(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()),
				userId);
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficUserByLastDay(long userId) {
		TrafficHistory traffic = trafficHistoryDAOImpl.getDownloadTrafficUserByDates(
				TimeStampManager.getStartOfDay(TimeStampManager
						.getCurrentTime()), TimeStampManager.getCurrentTime(),
				userId);
		return traffic.getSize();
	}

	@Override
	public double getUploadTrafficUserByLastMounth(long userId) {
		Long size = fileServiceImpl.getUploadTrafficUserByDates(
				TimeStampManager.getStartOfMonth(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfMonth(TimeStampManager.getCurrentTime()),
				userId);
		return size;
	}

	@Override
	public double getUploadTrafficUserByLastWeek(long userId) {
		Long size = fileServiceImpl.getUploadTrafficUserByDates(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()),
				userId);
		return size;
	}

	@Override
	public double getUploadTrafficUserByLastDay(long userId) {
		Long size = fileServiceImpl.getUploadTrafficUserByDates(
				TimeStampManager.getStartOfDay(TimeStampManager
						.getCurrentTime()), TimeStampManager.getCurrentTime(),
				userId);
		return size;
	}

}