package com.epam.lab.controller.dao.logger;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Log;

public class LogDAOImpl implements LogDAO {

	private DBQueryExecutor<Log> queryExecutor = new DBQueryExecutor<Log>();

	@Override
	public Log get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Log> getAll() {
		String sql = "SELECT * FROM logs";
		List<Log> resultList = queryExecutor.executeQuery(Log.class, sql);
		return resultList;
	}

	@Override
	public int insert(Log object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Log object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
