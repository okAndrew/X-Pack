package com.epam.lab.controller.dao.sessionhistory;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.SessionHistory;
import com.epam.lab.model.User;
import com.sun.jmx.snmp.Timestamp;

public class SessionHistoryDAOImpl implements SessionHistoryDAO {
	private DBQueryExecutor<SessionHistory> queryExecutor = new DBQueryExecutor<SessionHistory>();

	@Override
	public SessionHistory get(long id) {
		String sql = "SELECT * FROM session_history WHERE id=?";
		SessionHistory result = queryExecutor.executeQuerySingle(
				SessionHistory.class, sql, id);
		return result;
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

	@Override
	public SessionHistory getSessionHistByUserIDAndEndDate(long userId) {
		String sql = "SELECT * FROM session_history WHERE user_id=? AND enddate IS NULL";
		SessionHistory result = queryExecutor.executeQuerySingle(
				SessionHistory.class, sql, userId);
		return result;
	}

}