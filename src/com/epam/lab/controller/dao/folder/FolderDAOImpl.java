package com.epam.lab.controller.dao.folder;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Folder;

public class FolderDAOImpl implements FolderDAO {
	private DBQueryExecutor<Folder> queryExecutor = new DBQueryExecutor<Folder>();

	@Override
	public Folder get(long id) {
		String sql = "SELECT * FROM folders WHERE id = ?";
		Folder result = queryExecutor.executeQuerySingle(Folder.class, sql, id);
		return result;
	}

	public Folder getRoot(long userId) {
		String sql = "SELECT * FROM folders WHERE id_user = ? AND id_upper = 0";
		Folder folder = queryExecutor.executeQuerySingle(Folder.class, sql,
				userId);
		return folder;
	}

	public Folder getByUpperIdAndName(long upperId, String name) {
		String sql = "SELECT * FROM folders WHERE name = ? AND id_upper = ?";
		Folder folder = queryExecutor.executeQuerySingle(Folder.class, sql,
				name, upperId);
		return folder;
	}

	public List<Folder> getByUpperId(long upperId) {
		String sql = "SELECT * FROM folders WHERE id_upper = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql,
				upperId);
		return resultList;
	}

	@Override
	public List<Folder> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Folder> getAll(long userId) {
		String sql = "SELECT * FROM folders WHERE id_user = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql,
				userId);
		return resultList;
	}

	@Override
	public int insert(Folder object) {
		String sql = "INSERT INTO folders(id_user, name, id_upper, size, date) VALUES (?, ?, ?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, object.getIdUser(),
				object.getName(), object.getIdUpper(), object.getSize(),
				object.getDate());
		return result;
	}

	@Override
	public int update(Folder object) {
		String sql = "UPDATE folders SET id_user=?, name=?, id_upper=?, size=?, date=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, object.getIdUser(),
				object.getName(), object.getIdUpper(), object.getSize(),
				object.getDate(), object.getId());
		return result;
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM folders WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

}