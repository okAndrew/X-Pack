package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class SessionHistory {
	@TableColumn("id")
	private long id;

	@TableColumn("user_id")
	private Long userid;

	@TableColumn("startdate")
	private Timestamp startdate;

	@TableColumn("enddate")
	private Timestamp enddate;

	@TableColumn("sessIdTomcat")
	private String sessIdTomcat;

	public long getId() {
		return id;
	}

	public SessionHistory setId(long id) {
		this.id = id;
		return this;
	}

	public String getSessIdTomcat() {
		return sessIdTomcat;
	}

	public SessionHistory setSessIdTomcat(String sessIdTomcat) {
		this.sessIdTomcat = sessIdTomcat;
		return this;
	}

	public Long getUserid() {
		return userid;
	}

	public SessionHistory setUserid(Long userid) {
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
				+ ", startdate=" + startdate + ", enddate=" + enddate
				+ ", sessIdTomcat=" + sessIdTomcat + "]";
	}

}
