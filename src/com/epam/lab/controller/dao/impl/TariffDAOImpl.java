package com.epam.lab.controller.dao.impl;

import java.util.List;

import com.epam.lab.controller.dao.TariffDAO;
import com.epam.lab.controller.dao.querymanaging.DBQueryExecutor;
import com.epam.lab.model.Tariff;

public class TariffDAOImpl implements TariffDAO {
	private DBQueryExecutor<Tariff> queryExecutor = new DBQueryExecutor<Tariff>();
	
	public static final String INSERT_VALUES = "INSERT INTO tariffs(name, max_capacity) VALUES (?,?) ";
	public static final String DELETE_VALUES = "DELETE FROM tariffs WHERE id=?";
	public static final String SELECT_VALUES = "SELECT id, name, max_capacity FROM tariffs";
	public static final String SELECT_VALUES_BY_ID = "SELECT id, name, max_capacity FROM tariffs WHERE id=?";

	@Override
	public Tariff get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tariff> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Tariff object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Tariff object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
