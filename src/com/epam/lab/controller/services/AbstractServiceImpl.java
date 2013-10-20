package com.epam.lab.controller.services;

import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;

public class AbstractServiceImpl<T> implements AbstractService<T> {
	protected GenericDAO<T> dao;
	
	public AbstractServiceImpl(GenericDAO<T> dao){
		this.dao = dao;
	}
	
	@Override
	public List<T> getAll() {
		return dao.getAll();
	}

	@Override
	public T get(long id) {
		return dao.get(id);
	}

	@Override
	public int insert(T object) {
		return dao.insert(object);
	}

	@Override
	public int update(T object) {
		return dao.update(object);
	}

	@Override
	public int delete(long id) {
		return dao.delete(id);
	}
}
