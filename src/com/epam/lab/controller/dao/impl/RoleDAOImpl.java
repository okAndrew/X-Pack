package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.RoleDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Role;

public class RoleDAOImpl implements RoleDAO {
	private DBQueryExecutor<Role> queryExecutor = new DBQueryExecutor<Role>();

	@Override
	public int insert(Role role) {
		String sql = "INSERT INTO roles(name) VALUES (?)";
		int result = queryExecutor.executeUpdate(sql, role.getName());
		return result;
	}

	@Override
	public int update(Role role) {
		String sql = "UPDATE roles SET name=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, role.getName(),
				role.getId());
		return result;
	}

	@Override
	public Role get(long id) {
		String sql = "SELECT * FROM roles WHERE id=?";
		Role result = queryExecutor.executeQuerySingle(Role.class, sql, id);
		return result;
	}

	@Override
	public List<Role> getAll() {
		String sql = "SELECT * FROM roles";
		List<Role> resultList = queryExecutor.executeQuery(Role.class, sql);
		return resultList;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM roles WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

}
