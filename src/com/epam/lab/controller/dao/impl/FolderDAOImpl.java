package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.FolderDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Folder;

public class FolderDAOImpl implements FolderDAO {
	private DBQueryExecutor<Folder> queryExecutor = new DBQueryExecutor<Folder>();

	public static final String INSERT_VALUES = "INSERT INTO folders(user_id, name, upper) VALUES (?,?,?) ";
	public static final String DELETE_VALUES = "DELETE FROM folders WHERE id=?";
	public static final String SELECT_VALUES = "SELECT id, user_id, name, upper  FROM folders";
	public static final String SELECT_VALUES_BY_ID = "SELECT id, user_id, name, upper FROM folders WHERE id=?";

	@Override
	public Folder get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Folder> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Folder object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Folder object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}