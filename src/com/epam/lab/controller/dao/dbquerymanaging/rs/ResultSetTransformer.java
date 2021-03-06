package com.epam.lab.controller.dao.dbquerymanaging.rs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.lab.controller.annotations.TableColumn;
import com.epam.lab.controller.exceptions.NoSuchDAOObjectException;
import com.epam.lab.controller.exceptions.NoSuchDAOTypeException;

public class ResultSetTransformer<T> {
	private Logger logger = Logger.getLogger(this.getClass());
	private Class<?> type;

	public ResultSetTransformer() {
		super();
	}

	public ResultSetTransformer(Class<?> cls) {
		this.type = cls;
	}

	public T createObject(ResultSet rs) throws NoSuchDAOObjectException {
		// reflection magic
		Field[] fields = type.getDeclaredFields();
		T result = createInstance();
		RSManager rsManager = new RSManager();
		for (Field field : fields) {
			Annotation[] anns = field.getDeclaredAnnotations();
			TableColumn tColumn = findTableColumnAnn(anns);
			if (tColumn != null) {
				String colName = tColumn.value();
				Object value = null;
				try {
					value = rsManager.getObject(rs, colName, field.getType());
				} catch (NoSuchDAOTypeException e) {
					e.printStackTrace();
					logger.error(e);
				} catch (SQLException e) {
					// NOP. Quietly skip.
				}
				if (value == null)
					continue;
				field.setAccessible(true);
				try {
					field.set(result, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
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