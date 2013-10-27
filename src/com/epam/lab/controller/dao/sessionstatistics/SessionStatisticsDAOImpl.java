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
		String sql = "SELECT count(id) as count, timestamp(selected_date) as day FROM session_history  "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(startdate) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() group by selected_date";
		List<SessionStatistics> resultList = queryExecutor.executeQuery(
				SessionStatistics.class, sql);
		return resultList;
	}
	@Override
	public List<SessionStatistics> getAllByUserId(long userid) {
		String sql = "SELECT count(id) as count, timestamp(selected_date) as day FROM session_history  "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(startdate) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() AND (id is null or user_id = ?) group by selected_date";
		List<SessionStatistics> resultList = queryExecutor.executeQuery(
				SessionStatistics.class, sql, userid);
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
