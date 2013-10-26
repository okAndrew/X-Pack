package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class SessionStatistics {

	@TableColumn("count")
	private long count;

	@TableColumn("day")
	private Timestamp day;

	public SessionStatistics() {
	}

	public SessionStatistics(long count, Timestamp day) {
		super();
		this.count = count;
		this.day = day;
	}

	public Timestamp getDay() {
		return day;
	}

	public SessionStatistics setDay(Timestamp day) {
		this.day = day;
		return this;
	}

	public long getCount() {
		return count;
	}

	public SessionStatistics setCount(long count) {
		this.count = count;
		return this;
	}

	@Override
	public String toString() {
		return "SessionStatistics [count=" + count + ", day=" + day + "]";
	}

}