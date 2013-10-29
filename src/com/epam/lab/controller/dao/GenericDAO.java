package com.epam.lab.controller.dao;

import java.util.List;

public interface GenericDAO<T> {

	public T get(long id);

	public List<T> getAll();

	public int insert(T object);

	public int update(T object);

	public int delete(long id);

}
