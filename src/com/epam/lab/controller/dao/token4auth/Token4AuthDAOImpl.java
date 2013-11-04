package com.epam.lab.controller.dao.token4auth;

import java.util.Date;
import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Token4Auth;
import com.sun.jmx.snmp.Timestamp;

public class Token4AuthDAOImpl implements Token4AuthDAO {
	private DBQueryExecutor<Token4Auth> queryExecutor = new DBQueryExecutor<Token4Auth>();
	private Class<?> type = Token4Auth.class;

	@Override
	public Token4Auth get(long id) {
		String sql = "SELECT * FROM tokens4auth WHERE id=?";
		return queryExecutor.executeQuerySingle(type, sql, id);
	}

	@Override
	public List<Token4Auth> getAll() {
		String sql = "SELECT * FROM tokens4auth";
		return queryExecutor.executeQuery(type, sql);
	}

	@Override
	public int insert(Token4Auth token4Upload) {
		String sql = "INSERT INTO tokens4auth (id_user, token, destroy_date, id) VALUES(?, ?, ?, ?)";
		return queryExecutor.executeUpdate(sql, token4Upload.getIdUser(),
				token4Upload.getToken(), token4Upload.getDestroyDate(),
				token4Upload.getId());
	}

	@Override
	public int update(Token4Auth token4Upload) {
		String sql = "UPDATE SET tokens4auth=?, id_user=?, token=?, destroy_date=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, token4Upload.getIdUser(),
				token4Upload.getToken(), token4Upload.getDestroyDate(),
				token4Upload.getId());
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM tokens4auth WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

	@Override
	public Token4Auth getByToken(String token) {
		String sql = "SELECT * FROM tokens4auth WHERE token=?";
		return queryExecutor.executeQuerySingle(type, sql, token);
	}

	@Override
	public int deleteNotActiveTokens() {
		String sql = "DELETE FROM tokens4auth WHERE destroy_date < ?";
		Timestamp curTime = new Timestamp(new Date().getTime());
		return queryExecutor.executeUpdate(sql, curTime);
	}
}
