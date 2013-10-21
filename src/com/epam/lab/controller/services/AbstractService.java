package com.epam.lab.controller.services;

import java.util.List;

public interface AbstractService<T> {
	public T get(long id);

	public List<T> getAll();

	public int insert(T object);

	public int update(T object);

	public int delete(long id);
}
