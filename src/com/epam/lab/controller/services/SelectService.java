package com.epam.lab.controller.services;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.annotations.TableColumn;
import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.model.User;

public class SelectService<T> {
	
	static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	public List<T> getByParam(Class<?> type, String page, String count, String orderBy, String sop) {
		DBQueryExecutor<T> dbQueryExecutor = new DBQueryExecutor<T>();
		int p = getPage(page);
		int c = getCount(count);
		String order = getOrderBy(orderBy, type);
		String sort = getSort(sop);

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM ").append(type.getSimpleName().toLowerCase()).append("s");
		sql.append(" ORDER BY ").append(order);
		sql.append(" ").append(sort);
		sql.append(" LIMIT ").append(c);
		sql.append(" OFFSET ").append(p * c);

		return dbQueryExecutor.executeQuery(type, sql.toString());
	}
	
	private int getPage(String page) {
		int p = 0;

		if (page != null) {
			try {
				p = Integer.valueOf(page);
			} catch (NumberFormatException e) {
				logger.error(e);
				p = 0;
			}
		}

		return p;
	}

	private int getCount(String count) {
		int c = 10;

		if (count != null) {
			try {
				c = Integer.valueOf(count);
			} catch (NumberFormatException e) {
				logger.error(e);
				c = 10;
			}
		}
		
		if (c < 0 || c > 100) {
			c = 10;
		}

		return c;
	}

	private String getOrderBy(String orderBy, Class type) {
		String order = null;
		Field[] fields = type.getDeclaredFields();

		if (orderBy != null) {
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].isAnnotationPresent(TableColumn.class)) {
					TableColumn annotation = fields[i]
							.getAnnotation(TableColumn.class);
					if (annotation.value().equals(orderBy)) {
						order = annotation.value();
						break;
					}
				}
			}
		} else {
			order = "id";
		}

		return order;
	}

	private String getSort(String sop) {
		String sort = "asc";

		if (sop != null) {
			if (sop.toLowerCase().equals("asc")) {
				sort = "asc";
			} else if (sop.toLowerCase().equals("desc")) {
				sort = "desc";
			}
		}

		return sort;
	}

}
