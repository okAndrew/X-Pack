package com.epam.lab.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.epam.lab.controller.annotations.TableColumn;
import com.google.gson.annotations.Expose;

@XmlRootElement
public class Token4Auth {
	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long idUser;

	@TableColumn("token")
	@Expose
	@XmlElement(name = "token")
	private String token;

	@TableColumn("destroy_date")
	@Expose
	private Timestamp destroyDate;

	@XmlElement(name = "liveTime")
	private long liveTime;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destroyDate == null) ? 0 : destroyDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + (int) (liveTime ^ (liveTime >>> 32));
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		Token4Auth other = (Token4Auth) obj;
		if (destroyDate == null) {
			if (other.destroyDate != null)
				return false;
		} else if (!destroyDate.equals(other.destroyDate))
			return false;
		if (id != other.id)
			return false;
		if (idUser != other.idUser)
			return false;
		if (liveTime != other.liveTime)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
