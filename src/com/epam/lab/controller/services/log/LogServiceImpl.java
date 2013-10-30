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
	public long getCount() {
		return logDAO.getCount();
	}
}
