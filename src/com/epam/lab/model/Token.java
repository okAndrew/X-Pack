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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
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
		Token other = (Token) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (idUser != other.idUser)
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
