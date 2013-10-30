package com.epam.lab.controller.dao.logger;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Counter;
import com.epam.lab.model.Log;

public class LogDAOImpl implements LogDAO {

	private DBQueryExecutor<Log> queryExecutor = new DBQueryExecutor<Log>();
	private static Logger logger = Logger.getLogger(LogDAOImpl.class);

	@Override
	public Log get(long id) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
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
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public int update(Log object) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM logs WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

	@Override
	public long getCount() {
		String sql = "SELECT COUNT(id) AS countLogs FROM logs";
		Counter counter = new DBQueryExecutor<Counter>().executeQuerySingle(
				Counter.class, sql);
		return counter.getCountLogs();
	}

}
