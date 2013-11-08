package com.epam.lab.controller.dao.token;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.Token;

public class TokenDAOImpl implements TokenDAO {

	private DBQueryExecutor<Token> queryExecutor = new DBQueryExecutor<Token>();

	@Override
	public Token get(long id) {
		String sql = "SELECT * FROM tokens WHERE id=?";
		Token result = queryExecutor.executeQuerySingle(Token.class, sql, id);
		return result;
	}

	@Override
	public Token get(String token) {
		String sql = "SELECT * FROM tokens WHERE token=?";
		Token result = queryExecutor
				.executeQuerySingle(Token.class, sql, token);
		return result;
	}

	@Override
	public List<Token> getAll() {
		throw new NoSupportedActionException("No support this method");
	}

	@Override
	public int insert(Token object) {
		String sql = "INSERT INTO tokens (id_user, date, token) VALUES (?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, object.getIdUser(),
				object.getDate(), object.getToken());

		return result;
	}

	@Override
	public int update(Token object) {
		String sql = "UPDATE tokens SET id_user=?, date=?, token=?, available=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, object.getIdUser(),
				object.getDate(), object.getToken(), object.getAvailable(),
				object.getId());
		return result;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM tokens WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

}
