package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.FolderDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Folder;
import com.epam.lab.model.User;

public class FolderDAOImpl implements FolderDAO {
	private DBQueryExecutor<Folder> queryExecutor = new DBQueryExecutor<Folder>();

	public static final String INSERT_VALUES = "INSERT INTO folders(user_id, name, upper) VALUES (?,?,?) ";
	public static final String DELETE_VALUES = "DELETE FROM folders WHERE id=?";
	public static final String SELECT_VALUES = "SELECT id, user_id, name, upper  FROM folders";
	public static final String SELECT_VALUES_BY_ID = "SELECT id, user_id, name, upper FROM folders WHERE id=?";

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
		String sql = "INSERT INTO folders(id_user, name, id_upper) VALUES (?, ?, ?)";
		int result = queryExecutor.executeUpdate(sql, object.getIdUser(), object.getName(), object.getIdUpper());
		return result;
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
	public List<Folder> getAllbyUserId(long userId){
		String sql = "SELECT * FROM folders WHERE id_user = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql, userId);
		return  resultList;
	}

	public Folder getRootFolderByUserId(long idUser) {
		String sql = "SELECT * FROM folders WHERE id_user = ? AND id_upper = 0";
		Folder folder = queryExecutor.executeQuerySingle(Folder.class, sql, idUser);
		return folder;
	}

	public List<Folder> getAllbyUserIdAndUpperId(long idUser, long idUpper) {
		String sql = "SELECT * FROM folders WHERE id_user = ? AND id_upper = ?";
		List<Folder> resultList = queryExecutor.executeQuery(Folder.class, sql, idUser, idUpper);
		return  resultList;
	}

	public Folder getFolderByNameAndUpperId(long upperId, String folderName) {
		String sql = "SELECT * FROM folders WHERE name = ? AND id_upper = ?";
		Folder folder = queryExecutor.executeQuerySingle(Folder.class, sql, folderName, upperId);
		return folder;
	}
}