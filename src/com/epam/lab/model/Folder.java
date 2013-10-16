package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class Folder {
	@TableColumn("id")
	private long id;

	@TableColumn("id_user")
	private long idUser;

	@TableColumn("name")
	private String name;

	@TableColumn("id_upper")
	private long idUpper;
	
	@TableColumn("size")
	private double size;
	
	@TableColumn("date")
	private Timestamp date;

	public double getSize() {
		return size;
	}

	public Folder setSize(double size) {
		this.size = size;
		return this;
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