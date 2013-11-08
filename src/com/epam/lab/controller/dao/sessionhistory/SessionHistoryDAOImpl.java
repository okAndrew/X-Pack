package com.epam.lab.controller.dao.sessionhistory;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.SessionHistory;

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
		String sql = "INSERT INTO session_history(user_id, startdate, sessIdTomcat) VALUES (?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql,
				sessionObject.getUserid(), sessionObject.getStartdate(),
				sessionObject.getSessIdTomcat());
		return result;
	}

	@Override
	public int insertWithoutUser(SessionHistory sessionObject) {
		String sql = "INSERT INTO session_history(startdate, sessIdTomcat) VALUES (?, ?)";
		int result = queryExecutor.executeUpdate(sql,
				sessionObject.getStartdate(), sessionObject.getSessIdTomcat());
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
	public int setUserId(SessionHistory sessionObject) {
		String sql = "UPDATE session_history SET user_id=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql,
				sessionObject.getUserid(), sessionObject.getId());
		return result;
	}

	@Override
	public int delete(long id) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public SessionHistory getSessionHistBySessIdTomcat(String sessId) {
		String sql = "SELECT * FROM session_history WHERE sessIdTomcat=? ORDER BY id DESC LIMIT 1";
		SessionHistory result = queryExecutor.executeQuerySingle(
				SessionHistory.class, sql, sessId);
		return result;
	}

	@Override
	public List<SessionHistory> getAllVisitorsByDate(Timestamp startDate,
			Timestamp endtDate) {
		String sql = "SELECT * FROM session_history WHERE startdate BETWEEN ? AND ?";
		List<SessionHistory> resultList = queryExecutor.executeQuery(
				SessionHistory.class, sql, startDate, endtDate);
		return resultList;
	}

	@Override
	public List<SessionHistory> getLoggedVisitorsByDate(Timestamp startDate,
			Timestamp endtDate) {
		String sql = "SELECT * FROM session_history WHERE user_id IS NOT NULL AND startdate BETWEEN ? AND ? ";
		List<SessionHistory> resultList = queryExecutor.executeQuery(
				SessionHistory.class, sql, startDate, endtDate);
		return resultList;
	}

}