package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class Token4Download {

	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long user;

	@TableColumn("date_destroy")
	private Timestamp date;

	@TableColumn("token")
	private String token;

	@TableColumn("max_num_use")
	private int maxNumUse;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getMaxNumUse() {
		return maxNumUse;
	}

	public void setMaxNumUse(int maxNumUse) {
		this.maxNumUse = maxNumUse;
	}
}
