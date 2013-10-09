package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.FileDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.File;

public class FileDAOImpl implements FileDAO {
	private DBQueryExecutor<File> queryExecutor = new DBQueryExecutor<File>();

	public static final String INSERT_VALUES = "INSERT INTO files(folder, name_income, name, path, type, size, date, user) VALUES (?,?,?,?,?,?,?,?) ";
	public static final String DELETE_VALUES = "DELETE FROM files WHERE id=?";
	public static final String SELECT_VALUES = "SELECT id, folder, name_income, name, path, type, size, date, user  FROM files";
	public static final String SELECT_VALUES_BY_ID = "SELECT id, folder, name_income, name, path, type, size, date, user FROM files WHERE id=?";
	
	@Override
	public File get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<File> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insert(File object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(File object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
