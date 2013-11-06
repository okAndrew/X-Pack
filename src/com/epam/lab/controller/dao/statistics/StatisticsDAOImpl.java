package com.epam.lab.controller.dao.statistics;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Statistics;

public class StatisticsDAOImpl implements StatisticsDAO {

	private DBQueryExecutor<Statistics> queryExecutor = new DBQueryExecutor<Statistics>();
	private static Logger logger = Logger.getLogger(StatisticsDAOImpl.class);

	@Override
	public Statistics get(long id) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return null;
	}

	@Override
	public List<Statistics> getAll() {
		String sql = "SELECT count(id) as number, timestamp(selected_date) as day FROM session_history  "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(startdate) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() group by selected_date";
		return queryExecutor.executeQuery(Statistics.class, sql);
	}

	@Override
	public List<Statistics> getAllByUserId(long userid) {
		String sql = "SELECT count(id) as number, timestamp(selected_date) as day FROM session_history  "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(startdate) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() AND (id is null or user_id = ?) group by selected_date";
		return queryExecutor.executeQuery(Statistics.class, sql, userid);
	}

	@Override
	public int insert(Statistics object) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public int update(Statistics object) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public int delete(long id) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public List<Statistics> getAllDownloadStatistic() {
		String sql = "SELECT COALESCE(sum(size/1024/1024),0) as number, timestamp(selected_date) as day FROM traffic_history "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(date) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() group by selected_date";
		return queryExecutor.executeQuery(Statistics.class, sql);
	}

	@Override
	public List<Statistics> getAllDownloadStatisticByUserId(long userId) {
		String sql = "SELECT COALESCE(sum(size/1024/1024),0) as number, timestamp(selected_date) as day FROM traffic_history "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(date) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() AND (id is null or user_id=?) group by selected_date";
		return queryExecutor.executeQuery(Statistics.class, sql, userId);
	}

	@Override
	public List<Statistics> getAllUploadStatistic() {
		String sql = "SELECT COALESCE(sum(size/1024/1024),0) as number, timestamp(selected_date) as day FROM files "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(date) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() group by selected_date";
		return queryExecutor.executeQuery(Statistics.class, sql);
	}

	@Override
	public List<Statistics> getAllUploadStatisticByUserId(long userId) {
		String sql = "SELECT COALESCE(sum(size/1024/1024),0) as number, timestamp(selected_date) as day FROM files "
				+ "right join (select * from (select adddate('2013-01-01',t4*1000 + t3*100 + t2*10 + t1*1) selected_date "
				+ "from (select 0 t1 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t1, "
				+ "(select 0 t2 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t2, "
				+ "(select 0 t3 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t3, "
				+ "(select 0 t4 union select 1 union select 2 union select 3 union select 4 union select 5 union select 6 union select 7 union select 8 union select 9) t4) v) as dates on selected_date=date(date) "
				+ "where selected_date between '2013-09-01' and  CURRENT_DATE() AND (id is null or id_user=?) group by selected_date";
		return queryExecutor.executeQuery(Statistics.class, sql, userId);
	}

	@Override
	public Statistics getVisitsPerDayByUserId(long userId) {
		String sql = "select avg(countid) as number from(select count(id) as countid  FROM session_history "
				+ "where user_id = ? group by date(startdate)) as a ;";
		Statistics result = queryExecutor.executeQuerySingle(
				Statistics.class, sql, userId);
		return result;

	}

	@Override
	public Statistics getAvarageTimeSessionByUserId(long userId) {
		String sql = "select avg(to_seconds(enddate) - to_seconds(startdate))as number from session_history where user_id = ? and enddate is not null;";
		Statistics result = queryExecutor.executeQuerySingle(Statistics.class,
				sql, userId);
		return result;

	}

}