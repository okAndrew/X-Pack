package com.epam.lab.controller.dao.dbquerymanaging.rs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public enum GetterItem {
	INT(Integer.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getInt(colName);
		}
	},
	LONG(Long.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getLong(colName);
		}
	},
	DOUBLE(Double.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getDouble(colName);
		}
	},
	STRING(String.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getString(colName);
		}
	},
	BOOLEAN(Boolean.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getBoolean(colName);
		}
	},
	TIMESTAMP(Timestamp.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getTimestamp(colName);
		}
	};

	private Class<?> type;

	private GetterItem(Class<?> type) {
		this.type = type;
	}

	abstract Object executeGet(ResultSet rs, String colName)
			throws SQLException;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

}