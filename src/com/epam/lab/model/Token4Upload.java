package com.epam.lab.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.lab.controller.annotations.TableColumn;
import com.google.gson.annotations.Expose;

@XmlRootElement
public class Token4Upload {
	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long idUser;

	@Expose
	@TableColumn("token")
	@XmlElement(name = "token")
	private String token;

	@TableColumn("destroy_date")
	private Timestamp destroyDate;

	@Expose
	@XmlElement(name = "liveTime")
	private long liveTime;

	public boolean isActive() {
		boolean isActive = destroyDate
				.after(new Timestamp(new Date().getTime()));
		return isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public Timestamp getDestroyDate() {
		return destroyDate;
	}

	public void setDestroyDate(Timestamp destroyDate) {
		this.destroyDate = destroyDate;
	}

	public long getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(long liveTime) {
		this.liveTime = liveTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
