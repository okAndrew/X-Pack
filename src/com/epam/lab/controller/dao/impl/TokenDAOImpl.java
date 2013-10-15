package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.TokenDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
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
		String sql = "SELECT * FROM tokens WHERE tokens.token=?";
		Token result = queryExecutor.executeQuerySingle(Token.class, sql, token);
		return result;
	}

	@Override
	public List<Token> getAll() {
		return null;
	}

	@Override
	public int insert(Token object) {
		String sql = "INSERT INTO tokens (tokens.user, tokens.date, tokens.token) VALUES (?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, object.getUser(),
				object.getDate(), object.getToken());
		
		return result;
	}

	@Override
	public int update(Token object) {
		String sql = "UPDATE tokens SET user=? date=?, token=?, available=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, object.getUser(), object.getDate(),
				object.getToken(), object.getAvailable(), object.getId());
		return result;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM tokens WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

	@Override
	public int deactivateToken(String token) {
		String sql = "UPDATE tokens SET available=? WHERE tokens.token=?";
		int result = queryExecutor.executeUpdate(sql, 0, token);
		return result;
	}

	@Override
	public int activateToken(String token) {
		String sql = "UPDATE tokens SET available=? WHERE tokens.token=?";
		int result = queryExecutor.executeUpdate(sql, 1, token);
		return result;
	}

	@Override
	public int activateToken(Token token) {
		String sql = "UPDATE tokens SET available=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, 1, token.getId());
		return result;
	}

	@Override
	public int deactivateToken(Token token) {
		String sql = "UPDATE tokens SET available=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, 0, token.getId());
		return result;
	}

}
