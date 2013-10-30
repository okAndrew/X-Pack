package com.epam.lab.controller.dao.tariff;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Counter;
import com.epam.lab.model.Tariff;

public class TariffDAOImpl implements TariffDAO {

	private DBQueryExecutor<Tariff> queryExecutor = new DBQueryExecutor<Tariff>();

	@Override
	public Tariff get(long id) {
		String sql = "SELECT * FROM tariffs WHERE id=?";
		Tariff tariff = queryExecutor.executeQuerySingle(Tariff.class, sql, id);
		return tariff;
	}

	@Override
	public List<Tariff> getAll() {
		String sql = "SELECT * FROM tariffs";
		List<Tariff> tariffs = queryExecutor.executeQuery(Tariff.class, sql);
		return tariffs;
	}

	@Override
	public int insert(Tariff object) {
		String sql = "INSERT INTO tariffs(name, max_capacity, price, description, position) VALUES(?, ?, ?, ?, ?)";
		return queryExecutor.executeUpdate(sql, object.getName(),
				object.getMaxCapacity(), object.getPrice(), object.getDescription(), object.getPosition());
	}

	@Override
	public int update(Tariff object) {
		String sql = "UPDATE tariffs SET name=?, max_capacity=?, price=?, position=?, description=?, WHERE id=?";
		return queryExecutor.executeUpdate(sql, object.getName(),
				object.getMaxCapacity(), object.getPrice(), object.getPosition(), object.getDescription(), object.getId());
	}

	@Override
	public int delete(long id) {
		String sql = "UPDATE tariffs SET is_delete=true WHERE id=?";
		return queryExecutor.executeUpdate(sql, id);
	}

	 @Override
	 public List<Tariff> getAvailableTariffs() {
	  String sql = "SELECT * FROM tariffs WHERE is_delete = 0 ORDER BY position ASC";
	  return queryExecutor.executeQuery(Tariff.class, sql);
	 }

	@Override
	public int setIsDelete(boolean state, long id) {
		String sql = "UPDATE tariffs SET is_delete=? WHERE id=?";
		return queryExecutor.executeUpdate(sql, state, id);
	}

	@Override
	public long getCount() {
		String sql = "SELECT COUNT(id) AS countTariffs FROM tariffs";
		Counter counter = new DBQueryExecutor<Counter>().executeQuerySingle(
				Counter.class, sql);
		return counter.getCountTariffs();
	}
}
