package com.epam.lab.controller.dao.logger;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.Log;

public interface LogDAO extends GenericDAO<Log> {

	List<Log> getAll();

	long getCount();
}
