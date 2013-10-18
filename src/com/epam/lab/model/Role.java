package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Role {
	@TableColumn("id")
	private long id;

	@TableColumn("name")
	private String name;

	public long getId() {
		return id;
	}

	public Role setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}