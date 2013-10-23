package com.epam.lab.controller.dao.token4upload;

import java.util.Date;
import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Token4Upload;
import com.sun.jmx.snmp.Timestamp;

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
	public int insert(Token4Upload token4Upload) {
		String sql = "INSERT INTO tokens4upload (id_user, token, max_num_use, destroy_date, id) VALUES(?, ?, ?, ?, ?)";
		return queryExecutor.executeUpdate(sql, token4Upload.getIdUser(),
				token4Upload.getToken(), token4Upload.getMaxNumUse(),
				token4Upload.getDestroyDate(), token4Upload.getId());
	}

	@Override
	public int update(Token4Upload token4Upload) {
		String sql = "UPDATE SET tokens4upload=?, id_user=?, token=?, max_num_use=?, destroy_date=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, token4Upload.getIdUser(),
				token4Upload.getToken(), token4Upload.getMaxNumUse(),
				token4Upload.getDestroyDate(), token4Upload.getId());
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM tokens4upload WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

	public Token4Upload getByToken(String token) {
		String sql = "SELECT * FROM tokens4upload WHERE token=?";
		return queryExecutor.executeQuerySingle(type, sql, token);
	}

	public int deleteNotActiveTokens() {
		String sql = "DELETE FROM tokens4upload WHERE destroy_date < ? OR max_num_use<=0";
		return queryExecutor.executeUpdate(sql,
				new Timestamp(new Date().getTime()));
	}
}
