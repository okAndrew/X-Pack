package com.epam.lab.controller.dao.locale;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Locale;

public class LocaleDAOImpl implements LocaleDAO {
	private DBQueryExecutor<Locale> queryExecutor = new DBQueryExecutor<Locale>();

	@Override
	public Locale get(long id) {
		throw new NoSupportedActionException("No support this method");
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
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public int update(Locale object) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public int delete(long id) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public Locale getByLocale(String locale) {
		String sql = "SELECT lo.id, locale, la.name as language FROM locale lo join languages la"
				+ " on lo.language=la.id where locale=? union "
				+ "SELECT lo.id, locale, la.name as language FROM locale lo join languages la"
				+ " on lo.language=la.id where locale='en' and not exists(SELECT lo.id, locale, la.name "
				+ "as language FROM locale lo join languages la on lo.language=la.id where locale=?)";
		Locale result = queryExecutor.executeQuerySingle(Locale.class, sql,
				locale, locale);
		return result;
	}

}