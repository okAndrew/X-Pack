package com.epam.lab.controller.dao.querymanaging.pst;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum SetterItem {
	INT(Integer.class) {
		@Override
		void executeSet(PreparedStatement pst, int n, Object o)
				throws SQLException {
			pst.setInt(n, (Integer) o);
		}
	},
	LONG(Long.class) {
		@Override
		void executeSet(PreparedStatement pst, int n, Object o)
				throws SQLException {
			pst.setLong(n, (Long) o);
		}
	},
	DOUBLE(Double.class) {
		@Override
		void executeSet(PreparedStatement pst, int n, Object o)
				throws SQLException {
			pst.setDouble(n, (Double) o);
		}
	},
	STRING(String.class) {
		@Override
		void executeSet(PreparedStatement pst, int n, Object o)
				throws SQLException {
			pst.setString(n, (String) o);
		}
	},
	BOOLEAN(Boolean.class) {
		@Override
		void executeSet(PreparedStatement pst, int n, Object o)
				throws SQLException {
			pst.setBoolean(n, (Boolean) o);
		}
	};

	private Class<?> type;

	private SetterItem(Class<?> type) {
		this.type = type;
	}

	abstract void executeSet(PreparedStatement pst, int n, Object o)
			throws SQLException;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

}