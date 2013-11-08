package com.epam.lab.controller.dao.language;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Language;

public class LanguageDAOImpl implements LanguageDAO {

	private DBQueryExecutor<Language> queryExecutor = new DBQueryExecutor<Language>();

	@Override
	public Language get(long id) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public List<Language> getAll() {
		String sql = "SELECT * from languages";
		List<Language> resultList = queryExecutor.executeQuery(Language.class,
				sql);
		return resultList;
	}

	@Override
	public int insert(Language object) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public int update(Language object) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public int delete(long id) {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public Language getByLang(String language) {
		String sql = "SELECT * FROM languages where name=?";
		Language result = queryExecutor.executeQuerySingle(Language.class, sql,
				language);
		return result;
	}

}