package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.FileDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.File;

public class FileDAOImpl implements FileDAO {
	private DBQueryExecutor<File> queryExecutor = new DBQueryExecutor<File>();

	@Override
	public File get(long id) {
		String sql = "SELECT * FROM files WHERE id=?";
		File result = queryExecutor.executeQuerySingle(File.class, sql, id);
		return result;
	}

	@Override
	public List<File> getAll() {
		return null;
	}

	public List<File> getAllByUserId(long userId) {
		String sql = "SELECT * FROM files WHERE id_user = ?";
		List<File> resultList = queryExecutor.executeQuery(File.class, sql,
				userId);
		return resultList;
	}

	public List<File> getAllByFolderId(long folderId) {
		String sql = "SELECT * FROM files WHERE id_folder = ?";
		List<File> resultList = queryExecutor.executeQuery(File.class, sql,
				folderId);
		return resultList;
	}

	@Override
	public int insert(File object) {
		String sql = "INSERT INTO files (id_folder, name_income, name, path, type, size, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, object.getIdFolder(),
				object.getNameIncome(), object.getName(), object.getPath(),
				object.getType(), object.getSize(), object.getIdUser());
		return result;
	}

	@Override
	public int update(File object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM files WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

	public int deleteByFolderId(long id) {
		String sql = "DELETE FROM files WHERE id_folder=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}
}