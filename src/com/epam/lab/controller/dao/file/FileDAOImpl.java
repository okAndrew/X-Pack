package com.epam.lab.controller.dao.file;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.controller.exceptions.NoSupportedActionException;
import com.epam.lab.model.FilesTypesSize;
import com.epam.lab.model.UserFile;

public class FileDAOImpl implements FileDAO {
	
	private DBQueryExecutor<UserFile> queryExecutor = new DBQueryExecutor<UserFile>();
	
	@Override
	public UserFile get(long id) {
		String sql = "SELECT * FROM files WHERE id=?";
		UserFile result = queryExecutor.executeQuerySingle(UserFile.class, sql,
				id);
		return result;
	}
	
	@Override
	public UserFile getByName(String fName) {
		String sql = "SELECT * FROM files WHERE name=?";
		return queryExecutor.executeQuerySingle(UserFile.class, sql, fName);
	}

	@Override
	public UserFile getSizeUploadByDates(Timestamp dateStart, Timestamp dateEnd) {
		String sql = "SELECT SUM(size) AS size FROM files WHERE date BETWEEN ? AND ?";
		return queryExecutor.executeQuerySingle(UserFile.class, sql, dateStart,
				dateEnd);
	}

	@Override
	public UserFile getSizeUploadUserByDates(Timestamp dateStart,
			Timestamp dateEnd, long userId) {
		String sql = "SELECT SUM(size) AS size FROM files WHERE id_user=? AND date BETWEEN ? AND ?";
		return queryExecutor.executeQuerySingle(UserFile.class, sql, userId,
				dateStart, dateEnd);
	}

	@Override
	public List<UserFile> getAll() {
			throw new NoSupportedActionException("No support this method");
	}

	@Override
	public List<UserFile> getAllByUserId(long userId) {
		String sql = "SELECT * FROM files WHERE id_user = ?";
		List<UserFile> resultList = queryExecutor.executeQuery(UserFile.class,
				sql, userId);
		return resultList;
	}

	@Override
	public List<UserFile> getAllByFolderId(long folderId) {
		String sql = "SELECT * FROM files WHERE id_folder = ?";
		List<UserFile> resultList = queryExecutor.executeQuery(UserFile.class,
				sql, folderId);
		return resultList;
	}

	@Override
	public List<FilesTypesSize> getFilesGroupType() {
		String sql = "SELECT type, SUM(size) AS size FROM files GROUP BY(type)";
		List<FilesTypesSize> resultList = new DBQueryExecutor<FilesTypesSize>()
				.executeQuery(FilesTypesSize.class, sql);
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
		String sql = "UPDATE files SET id_folder=?, name_income=?, name=?, path=?, type=?, size=?, id_user=?, is_public=? WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, object.getIdFolder(),
				object.getNameIncome(), object.getName(), object.getPath(),
				object.getType(), object.getSize(), object.getIdUser(),
				object.getIsPublic(), object.getId());
		return result;
	}

	@Override
	public int updateSize(long id, long size) {
		String sql = "UPDATE files SET size=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, size, id);
	}

	@Override
	public int delete(long id) {
		String sql = "DELETE FROM files WHERE id=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

	@Override
	public int deleteByFolderId(long id) {
		String sql = "DELETE FROM files WHERE id_folder=?";
		int result = queryExecutor.executeUpdate(sql, id);
		return result;
	}

	@Override
	public int deleteByUserId(long userId) {
		String sql = "DELETE FROM files WHERE id_user=?";
		int result = queryExecutor.executeUpdate(sql, userId);
		return result;
	}
	

}
