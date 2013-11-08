package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;
import com.google.gson.annotations.Expose;

public class Folder {
	@Expose
	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long idUser;

	@Expose
	@TableColumn("name")
	private String name;

	@Expose
	@TableColumn("id_upper")
	private long idUpper;
	
	@Expose
	@TableColumn("size")
	private long size;
	
	@TableColumn("date")
	private Timestamp date;

	public long getSize() {
		return size;
	}

	public Folder setSize(long size) {
		this.size = size;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idUpper ^ (idUpper >>> 32));
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
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
		Folder other = (Folder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (idUpper != other.idUpper)
			return false;
		if (idUser != other.idUser)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	public Timestamp getDate() {
		return date;
	}

	public Folder setDate(Timestamp date) {
		this.date = date;
		return this;
	}

	public long getId() {
		return id;
	}

	public Folder setId(long id) {
		this.id = id;
		return this;
	}

	public long getIdUser() {
		return idUser;
	}

	public Folder setIdUser(long idUser) {
		this.idUser = idUser;
		return this;
	}

	public String getName() {
		return name;
	}

	public Folder setName(String name) {
		this.name = name;
		return this;
	}

	public long getIdUpper() {
		return idUpper;
	}

	public Folder setIdUpper(long idUpper) {
		this.idUpper = idUpper;
		return this;
	}

	@Override
	public String toString() {
		return "Folder [id=" + id + ", idUser=" + idUser + ", name=" + name
				+ ", idUpper=" + idUpper + ", size=" + size + ", date=" + date
				+ "]";
	}



}