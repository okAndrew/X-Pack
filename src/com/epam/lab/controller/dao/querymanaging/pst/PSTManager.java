package com.epam.lab.controller.dao.querymanaging.pst;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.lab.controller.exceptions.NoSuchDAOTypeException;

public class PSTManager {
	private static Logger logger = Logger.getLogger(PSTManager.class);

	// NoSuchDAOTypeException: throws or try/catch
	public void putArgs(PreparedStatement pst, Object args[])
			throws SQLException {
		int paramNumber = 1;
		for (Object o : args) {
			try {
				setObject(pst, paramNumber, o);
			} catch (NoSuchDAOTypeException e) {
				logger.error(e);
			}
			paramNumber++;
		}
	}

	private void setObject(PreparedStatement pst, int num, Object o)
			throws NoSuchDAOTypeException, SQLException {
		if (o == null) {
			pst.setObject(num, null);
			return;
		}
		SetterItem setter = new SetterCreator().findByType(o.getClass());
		if (setter == null) {
			pst.setObject(num, null);
			throw new NoSuchDAOTypeException();
		}
		setter.executeSet(pst, num, o);
	}
}