package com.epam.lab.controller.dao.tariff;

import java.util.List;

import com.epam.lab.controller.dao.dbquerymanaging.DBQueryExecutor;
import com.epam.lab.model.Counter;
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

		String sql = "select * from ("
				+ "select t.id,t.name,t.max_capacity,t.price, tt.text as description, t.position, t.is_delete "
				+ " from tariffs t "
				+ " inner join text_translation tt on t.description=tt.id "
				+ " inner join languages l on tt.lang=l.id "
				+ " where l.name=?"
				+ " union "
				+ " select t.id,t.name,t.max_capacity,t.price, tt.text as description,"
				+ " t.position, t.is_delete "
				+ " from tariffs t "
				+ " inner join text_translation tt on t.description=tt.id "
				+ " inner join languages l on tt.lang=l.id "
				+ " where l.name='English' and not exists( select * from tariffs t2 "
				+ " inner join text_translation tt on t2.description=tt.id "
				+ " inner join languages l on tt.lang=l.id "
				+ " where l.name=? and t2.id = t.id))as v;";
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

	@Override
	public long getCount() {
		String sql = "SELECT COUNT(id) AS countTariffs FROM tariffs";
		Counter counter = new DBQueryExecutor<Counter>().executeQuerySingle(
				Counter.class, sql);
		return counter.getCountTariffs();
	}

	@Override
	public List<Tariff> getByParam(Integer page, Integer count, String orderBy,
			String sop, String language) {
		StringBuilder sql = new StringBuilder();

		sql.append("select * from(select t.id,t.name,t.max_capacity,t.price, tt.text as description,");
		sql.append("t.position, t.is_delete from tariffs t ");
		sql.append("inner join text_translation tt on t.description=tt.id ");
		sql.append("inner join languages l on tt.lang=l.id ");
		sql.append("where l.name=? union ");
		sql.append("select t.id,t.name,t.max_capacity,t.price, tt.text as description,");
		sql.append("t.position, t.is_delete from tariffs t ");
		sql.append("inner join text_translation tt on t.description=tt.id ");
		sql.append("inner join languages l on tt.lang=l.id ");
		sql.append("where l.name='English' and not exists(	select * from tariffs t ");
		sql.append("inner join text_translation tt on t.description=tt.id ");
		sql.append("inner join languages l on tt.lang=l.id ");
		sql.append("where l.name=?))as v");
		sql.append(" ORDER BY ").append(orderBy);
		sql.append(" ").append(sop);
		sql.append(" LIMIT ").append(count);
		sql.append(" OFFSET ").append(page * count);

		return queryExecutor.executeQuery(Tariff.class, sql.toString(),
				language, language);
	}

	@Override
	public int insert(Tariff object, String descUA, String descRU) {
		String sql = "INSERT INTO text_translation(id, lang, text_translation.text) "
				+ " select id+1, 1, ?"
				+ " from text_translation ORDER BY id DESC LIMIT 1;";
		String sql2 = " INSERT INTO text_translation(id, lang, text_translation.text) "
				+ "select id, 2, ? "
				+ "from text_translation ORDER BY id DESC LIMIT 1;";
		String sql3 = "INSERT INTO text_translation(id, lang, text_translation.text) "
				+ "select id, 3, ? "
				+ "from text_translation ORDER BY id DESC LIMIT 1;";
		String sql4 = "INSERT INTO tariffs(name, max_capacity, price, description, position) "
				+ "select ?, ?, ?, id, ? "
				+ "from text_translation ORDER BY id DESC LIMIT 1;";

		
		queryExecutor.executeUpdate(sql, object.getDescription());
		queryExecutor.executeUpdate(sql2, descUA);
		queryExecutor.executeUpdate(sql3, descRU);
		queryExecutor.executeUpdate(sql4, object.getName(), object.getMaxCapacity(),
				 object.getPrice(), object.getPosition());
		return 0;
		// ConnectionManager connManager = ConnectionManager.getInstance();
		// int result = 0;
		// Statement pst = null;
		// Connection connection = null;
		// try {
		// connection = connManager.getConnection();
		// pst = connection.createStatement();
		// result = pst.executeUpdate(sql);
		// result = pst.executeUpdate(sql2);
		// result = pst.executeUpdate(sql3);
		// System.out.println(result);
		// } catch (SQLException e) {
		// throw new RuntimeException(e);
		// } finally {
		// connManager.closeQuality(connection);
		// }

		// +
		// "INSERT INTO tariffs(name, max_capacity, price, description, position) "
		// + "select ?, ?, ?, tt.id+1, ? "
		// + "from text_translation tt "
		// +
		// "inner join tariffs t on t.description=tt.id ORDER BY tt.id DESC LIMIT 1;";
		// return queryExecutor.executeUpdate(sql); // object.getDescription(),
		// descUA, descRU); //object.getName(), object.getMaxCapacity(),
		// object.getPrice(), object.getPosition());

	}
}
