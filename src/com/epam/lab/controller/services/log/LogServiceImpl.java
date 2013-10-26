package com.epam.lab.controller.services.log;

import java.util.List;

import com.epam.lab.controller.dao.logger.LogDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.Log;

public class LogServiceImpl extends AbstractServiceImpl<Log> implements
		LogService {

	private LogDAOImpl logDAO = new LogDAOImpl();

	public LogServiceImpl() {
		super(new LogDAOImpl());
	}

	public List<Log> getAll() {
		return logDAO.getAll();
	}

	@Override
	public List<Log> getErrorLogs() {
		return logDAO.getErrorLogs();
	}

	@Override
	public List<Log> getWarningLogs() {
		return logDAO.getWarningLogs();
	}

	@Override
	public List<Log> getInfoLogs() {
		return logDAO.getInfoLogs();
	}

	@Override
	public List<Log> getDebugLogs() {
		return logDAO.getDebagLogs();
	}

	@Override
	public void deleteLogs(String[] checkLogs) {
		for (int i = 0; i < checkLogs.length; i++) {
			logDAO.delete(Long.parseLong(checkLogs[i]));
		}
	}

	@Override
	public void clearTable() {
		logDAO.clearTable();
	}
}
