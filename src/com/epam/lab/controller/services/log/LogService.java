package com.epam.lab.controller.services.log;

import java.util.List;

import com.epam.lab.controller.services.AbstractService;
import com.epam.lab.model.Log;

public interface LogService extends AbstractService<Log> {

	long getCount();

	List<Log> getAll();

}
