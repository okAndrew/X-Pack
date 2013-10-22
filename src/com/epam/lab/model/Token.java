package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class Token {

	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long idUser;

	@TableColumn("date")
	private Timestamp date;

	@TableColumn("token")
	private String token;
	
	@TableColumn("available")
	private boolean available;

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
	
	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		StringBuilder tokenString = new StringBuilder();
		tokenString.append("Token [id=").append(id);
		tokenString.append(", idUser=").append(idUser);
		tokenString.append(", date=").append(date);
		tokenString.append(", token=").append(token);
		tokenString.append("];");

		return tokenString.toString();
	}

}
