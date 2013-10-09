package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Admin {
	@TableColumn("id")
	private long id;

	@TableColumn("login")
	private String login;

	@TableColumn("password")
	private String password;

	public long getId() {
		return id;
	}

	public Admin setId(long id) {
		this.id = id;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public Admin setLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public Admin setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", login=" + login + ", password="
				+ password + "]";
	}
}
