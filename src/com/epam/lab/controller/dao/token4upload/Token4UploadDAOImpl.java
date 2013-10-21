package com.epam.lab.controller.dao.token4upload;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Token4Upload;

public class Token4UploadDAOImpl implements Token4UploadDAO {
	private DBQueryExecutor<Token4Upload> queryExecutor = new DBQueryExecutor<Token4Upload>();
	private Class<?> type = Token4Upload.class;

	@Override
	public Token4Upload get(long id) {
		String sql = "SELECT * FROM tokens4upload WHERE id=?";
		return queryExecutor.executeQuerySingle(type, sql, id);
	}

	@Override
	public List<Token4Upload> getAll() {
		String sql = "SELECT * FROM tokens4upload";
		return queryExecutor.executeQuery(type, sql);
	}

	@Override
	public int insert(Token4Upload object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Token4Upload object) {
		return 0;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM tokens4upload WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

}
