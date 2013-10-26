package com.epam.lab.controller.dao.dbquerymanaging.rs;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.lab.controller.exceptions.NoSuchDAOTypeException;

public class RSManager {

	public Object getObject(ResultSet rs, String colName, Class<?> asType)
			throws NoSuchDAOTypeException, SQLException {
		GetterItem getter = new GetterCreator().findByType(asType);
		if (getter == null) {
			throw new NoSuchDAOTypeException();
		}
		return getter.executeGet(rs, colName);
	}
}