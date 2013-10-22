package com.epam.lab.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.lab.controller.annotations.TableColumn;
import com.google.gson.annotations.Expose;

@XmlRootElement
public class Token4Upload {
	/*
	 * @Expose - for build json from object without properties annotated by
	 * 
	 * @Expose
	 * 
	 * @XmlElement - for build object from json only with properties annotated
	 * by @XmlElement
	 */

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
	@TableColumn("max_num_use")
	@XmlElement(name = "maxNumUse")
	private int maxNumUse;

	@Expose
	@XmlElement(name = "liveTime")
	private long liveTime;

	public boolean isActive() {
		boolean isActive = destroyDate
				.after(new Timestamp(new Date().getTime())) && maxNumUse >= 0;
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

	public int getMaxNumUse() {
		return maxNumUse;
	}

	public void setMaxNumUse(int maxNumUse) {
		this.maxNumUse = maxNumUse;
	}
}
