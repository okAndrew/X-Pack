package com.epam.lab.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.lab.controller.annotations.TableColumn;

@XmlRootElement
public class Token4Download {

	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long user;

	@TableColumn("token")
	@XmlElement(name = "token")
	private String token;

	@TableColumn("date_destroy")
	private Timestamp date;
	
	@XmlElement(name = "liveTime")
	private long liveTime;

	@TableColumn("max_num_use")
	@XmlElement(name = "maxNumUse")
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
