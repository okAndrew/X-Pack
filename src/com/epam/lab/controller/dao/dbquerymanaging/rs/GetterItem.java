package com.epam.lab.controller.dao.dbquerymanaging.rs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public enum GetterItem {
	INT(Integer.TYPE, Integer.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getInt(colName);
		}
	},
	LONG(Long.TYPE, Long.class) {
		@Override
		Object executeGet(ResultSet rs, String colName) throws SQLException {
			return rs.getLong(colName);
		}
	},
	DOUBLE(Double.TYPE, Double.class) {
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
	BOOLEAN(Boolean.TYPE, Boolean.class) {
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

	private Class<?>[] types;

	private GetterItem(Class<?>... types) {
		this.types = types;
	}

	abstract Object executeGet(ResultSet rs, String colName)
			throws SQLException;

	public Class<?>[] getTypes() {
		return types;
	}

	public void setType(Class<?>[] types) {
		this.types = types;
	}
}