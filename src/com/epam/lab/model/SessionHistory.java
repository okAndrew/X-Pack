package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class SessionHistory {
	@TableColumn("id")
	private String id;

	@TableColumn("userid")
	private long userid;

	@TableColumn("startdate")
	private Timestamp startdate;

	@TableColumn("enddate")
	private Timestamp enddate;

	public String getId() {
		return id;
	}

	public SessionHistory setId(String id) {
		this.id = id;
		return this;
	}

	public long getUserid() {
		return userid;
	}

	public SessionHistory setUserid(long userid) {
		this.userid = userid;
		return this;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public SessionHistory setStartdate(Timestamp startdate) {
		this.startdate = startdate;
		return this;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public SessionHistory setEnddate(Timestamp enddate) {
		this.enddate = enddate;
		return this;
	}

	@Override
	public String toString() {
		return "SessionHistory [id=" + id + ", userid=" + userid
				+ ", startdate=" + startdate + ", enddate=" + enddate + "]";
	}

}
