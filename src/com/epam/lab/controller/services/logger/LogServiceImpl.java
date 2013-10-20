package com.epam.lab.controller.services.logger;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.controller.dao.logger.LogDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.Log;


public class LogServiceImpl extends AbstractServiceImpl<Log> implements
LogService{
	
	private static Logger logger = Logger.getLogger(LogServiceImpl.class);
	private LogDAOImpl logDAO = new LogDAOImpl();
	
	public LogServiceImpl() {
		super(new LogDAOImpl());
	}

	public List<Log> getAll(){
		return logDAO.getAll();
	}
}
