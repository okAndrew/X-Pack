package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.FileDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.UserFile;

public class FileDAOImpl implements FileDAO {
	private DBQueryExecutor<UserFile> queryExecutor = new DBQueryExecutor<UserFile>();

	@Override
	public UserFile get(long id) {
		String sql = "SELECT * FROM files WHERE id=?";
		UserFile result = queryExecutor.executeQuerySingle(UserFile.class, sql, id);
		return result;
	}

	@Override
	public List<UserFile> getAll() {
		return null;
	}

	public List<UserFile> getAllByUserId(long userId) {
		String sql = "SELECT * FROM files WHERE id_user = ?";
		List<UserFile> resultList = queryExecutor.executeQuery(UserFile.class, sql,
				userId);
		return resultList;
	}

	public List<UserFile> getAllByFolderId(long folderId) {
		String sql = "SELECT * FROM files WHERE id_folder = ?";
		List<UserFile> resultList = queryExecutor.executeQuery(UserFile.class, sql,
				folderId);
		return resultList;
	}

	@Override
	public int insert(UserFile object) {
		String sql = "INSERT INTO files (id_folder, name_income, name, path, type, size, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, object.getIdFolder(),
				object.getNameIncome(), object.getName(), object.getPath(),
				object.getType(), object.getSize(), object.getIdUser());
		return result;
	}

	@Override
	public int update(UserFile object) {
		String sql = "UPDATE files SET id_folder=?, name_income=?, name=?, path=?, type=?, size=?, id_user=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, object.getIdFolder(),
				object.getNameIncome(), object.getName(), object.getPath(),
				object.getType(), object.getSize(), object.getIdUser(),
				object.getId());
		return result;
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