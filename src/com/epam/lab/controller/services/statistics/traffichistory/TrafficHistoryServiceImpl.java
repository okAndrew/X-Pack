package com.epam.lab.controller.services.statistics.traffichistory;

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
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficByLastWeek() {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficByDates(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()));
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficByLastDay() {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficByDates(
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
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficUserByDates(
				TimeStampManager.getStartOfMonth(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfMonth(TimeStampManager.getCurrentTime()),
				userId);
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficUserByLastWeek(long userId) {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficUserByDates(
				TimeStampManager.getStartOfWeek(TimeStampManager
						.getCurrentTime()), TimeStampManager
						.getEndOfWeek(TimeStampManager.getCurrentTime()),
				userId);
		return traffic.getSize();
	}

	@Override
	public double getDownloadTrafficUserByLastDay(long userId) {
		TrafficHistory traffic = traDaoImpl.getDownloadTrafficUserByDates(
				TimeStampManager.getStartOfDay(TimeStampManager
						.getCurrentTime()), TimeStampManager.getCurrentTime(),
				userId);
		return traffic.getSize();
	}

	@Override
	public double getUploadTrafficUserByLastMounth(long userId) {
		Long size = fileServiceImpl.getUploadTrafficUserByDates(TimeStampManager
				.getStartOfMonth(TimeStampManager.getCurrentTime()),
				TimeStampManager.getEndOfMonth(TimeStampManager
						.getCurrentTime()), userId);
		return size;
	}

	@Override
	public double getUploadTrafficUserByLastWeek(long userId) {
		Long size = fileServiceImpl
				.getUploadTrafficUserByDates(TimeStampManager
						.getStartOfWeek(TimeStampManager.getCurrentTime()),
						TimeStampManager.getEndOfWeek(TimeStampManager
								.getCurrentTime()), userId);
		return size;
	}

	@Override
	public double getUploadTrafficUserByLastDay(long userId) {
		Long size = fileServiceImpl.getUploadTrafficUserByDates(TimeStampManager
				.getStartOfDay(TimeStampManager.getCurrentTime()),
				TimeStampManager.getCurrentTime(), userId);
		return size;
	}

}