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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enddate == null) ? 0 : enddate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((sessIdTomcat == null) ? 0 : sessIdTomcat.hashCode());
		result = prime * result
				+ ((startdate == null) ? 0 : startdate.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionHistory other = (SessionHistory) obj;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (id != other.id)
			return false;
		if (sessIdTomcat == null) {
			if (other.sessIdTomcat != null)
				return false;
		} else if (!sessIdTomcat.equals(other.sessIdTomcat))
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
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
