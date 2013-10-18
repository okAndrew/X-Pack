package com.epam.lab.controller.dao.querymanaging.rs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.lab.controller.annotations.TableColumn;
import com.epam.lab.controller.exceptions.NoSuchDAOObjectException;

public class ResultSetTransformer<T> {
	private Logger logger = Logger.getLogger(this.getClass());
	private Class<?> type;

	public ResultSetTransformer() {
		super();
	}

	public ResultSetTransformer(Class<?> cls) {
		this.type = cls;
	}

	public T createObject(ResultSet rs) throws SQLException,
			NoSuchDAOObjectException {
		// reflection magic
		Field[] fields = type.getDeclaredFields();
		T result = createInstance();
		for (Field field : fields) {
			Annotation[] anns = field.getDeclaredAnnotations();
			TableColumn tColumn = findTableColumnAnn(anns);
			if (tColumn != null) {
				String colName = tColumn.value();
				Object atr = rs.getObject(colName);
				field.setAccessible(true);
				try {
					field.set(result, atr);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					logger.error(e);
				}
			}

		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private T createInstance() throws NoSuchDAOObjectException {
		T result = null;
		try {
			result = (T) type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e);
			throw new NoSuchDAOObjectException("Error constructor execute");
		}
		return result;
	}

	public TableColumn findTableColumnAnn(Annotation anns[]) {
		TableColumn resultAnn = null;
		for (Annotation a : anns) {
			if (a instanceof TableColumn) {
				resultAnn = (TableColumn) a;
				break;
			}
		}
		return resultAnn;
	}
}