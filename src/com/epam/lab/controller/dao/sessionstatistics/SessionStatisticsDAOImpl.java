package com.epam.lab.controller.dao.sessionstatistics;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.SessionStatistics;
import com.epam.lab.model.User;

public class SessionStatisticsDAOImpl implements SessionStatisticsDAO {
	private DBQueryExecutor<SessionStatistics> queryExecutor = new DBQueryExecutor<SessionStatistics>();

	@Override
	public SessionStatistics get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionStatistics> getAll() {
		String sql = "SELECT count(*) as count, date(startdate) as day FROM session_history group by date(startdate)";
		List<SessionStatistics> resultList = queryExecutor.executeQuery(SessionStatistics.class, sql);
		return resultList;
	}

	@Override
	public int insert(SessionStatistics object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SessionStatistics object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
