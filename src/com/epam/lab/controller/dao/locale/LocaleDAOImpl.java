package com.epam.lab.controller.dao.locale;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Locale;

public class LocaleDAOImpl implements LocaleDAO {
	private DBQueryExecutor<Locale> queryExecutor = new DBQueryExecutor<Locale>();
	private static Logger logger = Logger.getLogger(Locale.class);

	@Override
	public Locale get(long id) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return null;
	}

	@Override
	public List<Locale> getAll() {
		String sql = "SELECT lo.id, locale, la.name FROM locale lo join languages la"
				+ " on lo.language=la.id";
		List<Locale> resultList = queryExecutor.executeQuery(Locale.class, sql);
		return resultList;
	}

	@Override
	public int insert(Locale object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Locale object) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public int delete(long id) {
		try {
			throw new NoSupportedActionException("No support this method");
		} catch (NoSupportedActionException e) {
			logger.error("use no suropted method" + e);
		}
		return 0;
	}

	@Override
	public Locale getByLocale(String locale) {
		String sql = "SELECT lo.id, locale, la.name as language FROM locale lo join languages la"
				+ " on lo.language=la.id where locale=?";
		Locale result = queryExecutor.executeQuerySingle(Locale.class, sql,
				locale);
		return result;
	}

}