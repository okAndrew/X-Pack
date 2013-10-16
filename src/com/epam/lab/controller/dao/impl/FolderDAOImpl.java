package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.FolderDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Folder;

public class FolderDAOImpl implements FolderDAO {
	private DBQueryExecutor<Folder> queryExecutor = new DBQueryExecutor<Folder>();

	@Override
	public Folder get(long id) {
		String sql = "SELECT * FROM folders WHERE id = ?";
		Folder result = queryExecutor.executeQuerySingle(Folder.class, sql, id);
		return result;
	}

	@Override
	public List<Folder> getAll() {
		// TODO Auto-generated method stub
		return null;
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

	public List<Folder> getAll(long userId) {
		String sql = "SELECT * FROM folders WHERE id_user = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql,
				userId);
		return resultList;
	}
	
	public List<Folder> getFoldersByUpperId(long upperId) {
		String sql = "SELECT * FROM folders WHERE id_upper = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql,
				upperId);
		return resultList;
	}

	public Folder getRootFolder(long idUser) {
		String sql = "SELECT * FROM folders WHERE id_user = ? AND id_upper = 0";
		Folder folder = queryExecutor.executeQuerySingle(Folder.class, sql,
				idUser);
		return folder;
	}

	public List<Folder> getAllbyUserIdAndUpperId(long idUser, long idUpper) {
		String sql = "SELECT * FROM folders WHERE id_user = ? AND id_upper = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql,
				idUser, idUpper);
		return resultList;
	}

	public Folder getFolderByNameAndUpperId(long upperId, String folderName) {
		String sql = "SELECT * FROM folders WHERE name = ? AND id_upper = ?";
		Folder folder = queryExecutor.executeQuerySingle(Folder.class, sql,
				folderName, upperId);
		return folder;
	}
}