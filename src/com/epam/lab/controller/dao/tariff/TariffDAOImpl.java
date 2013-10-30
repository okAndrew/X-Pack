package com.epam.lab.controller.dao.tariff;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Tariff;

public class TariffDAOImpl implements TariffDAO {

	private DBQueryExecutor<Tariff> queryExecutor = new DBQueryExecutor<Tariff>();

	@Override
	public Tariff get(long id) {
		String sql = "select t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete "
				+ " from tariffs t join text_translation tt "
				+ "on t.description=tt.id join languages l on tt.lang=l.id "
				+ "where l.name='English' and t.id=?";
		Tariff tariff = queryExecutor.executeQuerySingle(Tariff.class, sql, id);
		return tariff;
	}

	@Override
	public Tariff get(long id, String language) {
		String sql = "select t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete "
				+ " from tariffs t join text_translation tt "
				+ "on t.description=tt.id join languages l on tt.lang=l.id "
				+ "where l.name=? and t.id=?";
		Tariff tariff = queryExecutor.executeQuerySingle(Tariff.class, sql,
				language, id);
		return tariff;
	}

	@Override
	public List<Tariff> getAll() {
		String sql = "select t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete "
				+ "from tariffs t "
				+ "inner join text_translation tt on t.description=tt.id "
				+ "inner join languages l on tt.lang=l.id "
				+ "where l.name='English'";
		List<Tariff> tariffs = queryExecutor.executeQuery(Tariff.class, sql);
		return tariffs;
	}

	@Override
	public List<Tariff> getAll(String language) {

		String sql = "select * from(select t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete "
				+ "from tariffs t "
				+ "inner join text_translation tt on t.description=tt.id "
				+ "inner join languages l on tt.lang=l.id "
				+ "where l.name=? "
				+ "union "
				+ "select t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete "
				+ "from tariffs t "
				+ "inner join text_translation tt on t.description=tt.id "
				+ "inner join languages l on tt.lang=l.id "
				+ "where l.name='English' and not exists(	select * from tariffs t "
				+ "inner join text_translation tt on t.description=tt.id "
				+ "inner join languages l on tt.lang=l.id "
				+ "where l.name=?)"
				+ ")as v";
		List<Tariff> tariffs = queryExecutor.executeQuery(Tariff.class, sql,
				language, language);
		return tariffs;
	}

	@Override
	public int insert(Tariff object) {
		String sql = "INSERT INTO tariffs(name, max_capacity, price, description, position) VALUES(?, ?, ?, ?, ?)";
		return queryExecutor.executeUpdate(sql, object.getName(),
				object.getMaxCapacity(), object.getPrice(),
				object.getDescription(), object.getPosition());
	}

	@Override
	public int update(Tariff object) {
		String sql = "UPDATE tariffs SET name=?, max_capacity=?, price=?, position=?, description=?, WHERE id=?";
		return queryExecutor.executeUpdate(sql, object.getName(),
				object.getMaxCapacity(), object.getPrice(),
				object.getPosition(), object.getDescription(), object.getId());
	}

	@Override
	public int delete(long id) {
		String sql = "UPDATE tariffs SET is_delete=true WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

	@Override
	public List<Tariff> getAvailableTariffs() {
		String sql = "SELECT t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete FROM tariffs t join text_translation tt "
				+ "on t.description=tt.id join languages l on tt.lang=l.id "
				+ "WHERE l.name='English' and is_delete = 0 ORDER BY position ASC";
		return queryExecutor.executeQuery(Tariff.class, sql);
	}

	@Override
	public List<Tariff> getAvailableTariffs(String language) {
		String sql = "SELECT t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ "t.position, t.is_delete FROM tariffs t join text_translation tt "
				+ "on t.description=tt.id join languages l on tt.lang=l.id "
				+ "WHERE l.name=? and is_delete = 0 ORDER BY position ASC";
		return queryExecutor.executeQuery(Tariff.class, sql, language);
	}

	@Override
	public int setIsDelete(boolean state, long id) {
		String sql = "UPDATE tariffs SET is_delete=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, state, id);
	}
}
