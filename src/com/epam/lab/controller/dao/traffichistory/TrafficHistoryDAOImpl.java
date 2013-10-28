package com.epam.lab.controller.dao.traffichistory;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.TrafficHistory;

public class TrafficHistoryDAOImpl implements TrafficHistoryDAO {

	private DBQueryExecutor<TrafficHistory> queryExecutor = new DBQueryExecutor<TrafficHistory>();

	@Override
	public TrafficHistory get(long id) {
		String sql = "SELECT * FROM traffic_history WHERE id=?";
		return queryExecutor.executeQuerySingle(TrafficHistory.class, sql, id);
	}

	@Override
	public List<TrafficHistory> getAll() {
		String sql = "SELECT * FROM traffic_history";
		return queryExecutor.executeQuery(TrafficHistory.class, sql);
	}

	@Override
	public int insert(TrafficHistory object) {
		String sql = "INSERT INTO traffic_history(user_id, date, size) VALUES(?, ?, ?)";
		return queryExecutor.executeUpdate(sql, object.getUserId(), object.getDate(), object.getSize());
	}

	@Override
	public int update(TrafficHistory object) {
		String sql = "UPDATE traffic_history SET user_id=? date=? size=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, object.getUserId(), object.getDate(), object.getSize(), object.getId());
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM traffic_history WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

	@Override
	public TrafficHistory getDownloadTrafficByDates(Timestamp dateStart,
			Timestamp dateEnd) {
		String sql = "SELECT SUM(`size`) AS size FROM traffic_history WHERE date BETWEEN ? AND ?";
		return queryExecutor.executeQuerySingle(TrafficHistory.class, sql, dateStart, dateEnd);
	}
}
