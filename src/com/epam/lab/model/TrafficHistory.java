package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class TrafficHistory {

	@TableColumn("id")
	private long id;

	@TableColumn("user_id")
	private long userId;

	@TableColumn("date")
	private Timestamp date;

	@TableColumn("size")
	private int size;

	public long getId() {
		return id;
	}

	public TrafficHistory setId(long id) {
		this.id = id;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public TrafficHistory setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public Timestamp getDate() {
		return date;
	}

	public TrafficHistory setDate(Timestamp date) {
		this.date = date;
		return this;
	}

	public int getSize() {
		return size;
	}

	public TrafficHistory setSize(int size) {
		this.size = size;
		return this;
	}

}
