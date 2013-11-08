package com.epam.lab.controller.dao.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ConnectionManager {

	private static final Logger logger = Logger
			.getLogger(ConnectionManager.class);
	private static ConnectionManager connManager = null;
	private InitialContext initContext = null;
	private DataSource dataSource = null;

	private ConnectionManager() {
	}

	public static ConnectionManager newInstance() {
		connManager = new ConnectionManager();
		try {
			connManager.initContext = new InitialContext();
			Context envContext = (Context) connManager.initContext
					.lookup("java:/comp/env");
			connManager.dataSource = (DataSource) envContext
					.lookup("datasource");
		} catch (NamingException e) {
			logger.error(e);
			e.printStackTrace();
			connManager = null;
		}
		return connManager;
	}

	public static ConnectionManager  getInstance() {
		if (connManager == null) {
			connManager = newInstance();
		}
		return connManager;
	}
	
	public Connection getConnection() throws SQLException {
		Connection result = null;
		 if (connManager != null)
		 result = dataSource.getConnection();
//		 for tests
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			result = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/dreamhost", "root", "1111");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		return result;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void closeQuality(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error close connection!!!");
		}
	}

	public void closeQuality(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error close preparedStatemant!!!");
		}
	}

	public void closeQuality(Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error close statemant!!!");
		}
	}

	public void closeQuality(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error close resultSet!!!");
		}
	}

	public void close(ResultSet resultSet, PreparedStatement prepareStatement,
			Connection connection) {

		closeQuality(resultSet);
		closeQuality(prepareStatement);
		closeQuality(connection);
	}

	public void close(PreparedStatement prepareStatement, Connection connection) {
		closeQuality(prepareStatement);
		closeQuality(connection);
	}

	public void close(ResultSet resultSet, Statement statement,
			Connection connection) {
		closeQuality(resultSet);
		closeQuality(statement);
		closeQuality(connection);
	}
}
