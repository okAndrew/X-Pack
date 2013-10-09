package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Tariff {
	@TableColumn("id")
	private long id;

	@TableColumn("name")
	private String name;

	@TableColumn("max_capacity")
	private int maxCapacity;

	public long getId() {
		return id;
	}

	public Tariff setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Tariff setName(String name) {
		this.name = name;
		return this;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public Tariff setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}

	@Override
	public String toString() {
		return "Tariff [id=" + id + ", name=" + name + ", maxCapacity="
				+ maxCapacity + "]";
	}

}