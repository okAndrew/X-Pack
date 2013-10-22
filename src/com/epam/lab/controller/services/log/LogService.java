package com.epam.lab.controller.services.log;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Log;

public interface LogService extends AbstractService<Log> {

	List<Log> getAll();

	List<Log> getErrorLogs();

	List<Log> getWarningLogs();

	List<Log> getInfoLogs();

	List<Log> getDebugLogs();
	
	String deleteLogs(String[] checkLogs);
	
	void clearTable();
}
