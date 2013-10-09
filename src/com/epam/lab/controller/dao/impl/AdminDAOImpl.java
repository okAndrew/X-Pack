package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.AdminDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Admin;

public class AdminDAOImpl implements AdminDAO {
	private DBQueryExecutor<Admin> queryExecutor = new DBQueryExecutor<Admin>();

	@Override
	public Admin get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Admin object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Admin object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Admin getByLogin(String login) {
		String sql = "SELECT * FROM admins WHERE login=?";
		List<Admin> resultList = queryExecutor.executeQuery(Admin.class, sql, login);
		return resultList.get(0);
	}

}
