package com.epam.lab.controller.dao.querymanaging.rs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.lab.controller.annotations.TableColumn;

public class ResultSetTransformer<T> {
	private Class<?> type;

	public ResultSetTransformer() {
	}

	public ResultSetTransformer(Class<?> cls) {
		this.type = cls;
	}

	// rewrite this
	@SuppressWarnings("unchecked")
	public T getObject(ResultSet rs) throws SQLException {
		// reflection magic
		Field[] fields = type.getDeclaredFields();
		T result = null;
		try {
			result = (T) type.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		for (Field field : fields) {
			Annotation[] anns = field.getDeclaredAnnotations();
			if (anns.length < 1) {
				continue;
			}
			if (anns[0] instanceof TableColumn) {
				TableColumn tColumn = (TableColumn) anns[0];
				String colName = tColumn.value();
				Object atr = rs.getObject(colName);
				field.setAccessible(true);
				try {
					field.set(result, atr);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}

		}
		return result;
	}
}