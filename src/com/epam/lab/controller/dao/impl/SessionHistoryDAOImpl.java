package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.SessionHistoryDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryDAOImpl implements SessionHistoryDAO {
	private DBQueryExecutor<SessionHistory> queryExecutor = new DBQueryExecutor<SessionHistory>();

	@Override
	public SessionHistory get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionHistory> getAll() {
		String sql = "SELECT * FROM session_history";
		List<SessionHistory> resultList = queryExecutor.executeQuery(
				SessionHistory.class, sql);
		return resultList;
	}

	@Override
	public int insert(SessionHistory sessionObject) {
		String sql = "INSERT INTO session_history(user_id, startdate) VALUES (?, ?)";
		int result = queryExecutor.executeUpdate(sql,
				sessionObject.getUserid(), sessionObject.getStartdate());
		return result;
	}

	@Override
	public int update(SessionHistory sessionObject) {
		String sql = "UPDATE session_history SET enddate=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql,
				sessionObject.getEnddate(), sessionObject.getId());
		return result;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
