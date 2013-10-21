package com.epam.lab.controller.dao.dbquerymanaging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.dbconnect.ConnectionManager;
import com.epam.lab.controller.dao.dbquerymanaging.pst.PSTManager;
import com.epam.lab.controller.dao.dbquerymanaging.rs.ResultSetTransformer;
import com.epam.lab.controller.exceptions.NoSuchDAOObjectException;

/*
 * T - type of the returned list (need only for executeQuery)
 * Class<?> type - temp. argument... need for reflection... later it will be taken away
 */

public class DBQueryExecutor<T> {
	private static ConnectionManager connManager = ConnectionManager
			.getInstance();
	private static Logger logger = Logger.getLogger(DBQueryExecutor.class);

	/*
	 * INSERT, UPDATE, DELETE
	 * with parameters
	 */
	public int executeUpdate(String sql, Object... args) {
		int result = 0;
		PreparedStatement pst = null;
		Connection connection = null;
		try {
			connection = connManager.getConnection();
			pst = connection.prepareStatement(sql);
			new PSTManager().putArgs(pst, args);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			connManager.closeQuality(connection);
		}
		return result;
	}

	/*
	 * SELECT
	 * with parameters
	 */
	public List<T> executeQuery(Class<?> type, String sql, Object... args) {
		List<T> resultList = new ArrayList<T>();
		PreparedStatement pst = null;
		Connection connection = null;
		try {
			connection = connManager.getConnection();
			pst = connection.prepareStatement(sql);
			if (args != null && args.length > 0)
				new PSTManager().putArgs(pst, args);
			ResultSet rs = pst.executeQuery();
			ResultSetTransformer<T> resultSetManager = new ResultSetTransformer<T>(
					type);
			while (rs.next()) {
				resultList.add(resultSetManager.createObject(rs));
			}
		} catch (SQLException | NoSuchDAOObjectException e) {
			logger.error(e);
		} finally {
			connManager.closeQuality(connection);
		}
		return resultList;
	}

	/*
	 * SELECT
	 * without parameters
	 */
	public List<T> executeQuery(Class<?> type, String sql) {
		List<T> resultList = new ArrayList<T>();
		Statement st = null;
		Connection connection = null;
		try {
			connection = connManager.getConnection();
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetTransformer<T> resultSetManager = new ResultSetTransformer<T>(
					type);
			while (rs.next()) {
				resultList.add(resultSetManager.createObject(rs));
			}
		} catch (SQLException | NoSuchDAOObjectException e) {
			logger.error(e);
		} finally {
			connManager.closeQuality(connection);
		}
		return resultList;
	}

	/*
	 * SELECT
	 * with parameters
	 * return only one object
	 */
	public T executeQuerySingle(Class<?> type, String sql, Object...args) {
		T result = null;
		PreparedStatement pst = null;
		Connection connection = null;
		try {
			connection = connManager.getConnection();
			pst = connection.prepareStatement(sql);
			if (args != null && args.length > 0)
				new PSTManager().putArgs(pst, args);
			ResultSet rs = pst.executeQuery();
			ResultSetTransformer<T> resultSetManager = new ResultSetTransformer<T>(
					type);
			if (rs.next()) {
				result = resultSetManager.createObject(rs);
			}
		} catch (SQLException | NoSuchDAOObjectException e) {
			logger.error(e);
		} finally {
			connManager.closeQuality(connection);
		}
		return result;
	}
}
