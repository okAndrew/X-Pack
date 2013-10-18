package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class UserFile {
	@TableColumn("id")
	private long id;

	@TableColumn("id_folder")
	private long idFolder;

	@TableColumn("name_income")
	private String nameIncome;

	@TableColumn("name")
	private String name;

	@TableColumn("path")
	private String path;

	@TableColumn("type")
	private String type;

	@TableColumn("size")
	private double size;

	@TableColumn("date")
	private Timestamp date;

	@TableColumn("id_user")
	private long idUser;

	public long getId() {
		return id;
	}

	public UserFile setId(long id) {
		this.id = id;
		return this;
	}

	public long getIdFolder() {
		return idFolder;
	}

	public UserFile setIdFolder(long idFolder) {
		this.idFolder = idFolder;
		return this;
	}

	public String getNameIncome() {
		return nameIncome;
	}

	public UserFile setNameIncome(String nameIncome) {
		this.nameIncome = nameIncome;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserFile setName(String name) {
		this.name = name;
		return this;
	}

	public String getPath() {
		return path;
	}

	public UserFile setPath(String path) {
		this.path = path;
		return this;
	}

	public String getType() {
		return type;
	}

	public UserFile setType(String type) {
		this.type = type;
		return this;
	}

	public double getSize() {
		return size;
	}

	public UserFile setSize(double size) {
		this.size = size;
		return this;
	}

	public Timestamp getDate() {
		return date;
	}

	public UserFile setDate(Timestamp date) {
		this.date = date;
		return this;
	}

	public long getIdUser() {
		return idUser;
	}

	public UserFile setIdUser(long idUser) {
		this.idUser = idUser;
		return this;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", idFolder=" + idFolder + ", nameIncome="
				+ nameIncome + ", name=" + name + ", path=" + path + ", type="
				+ type + ", size=" + size + ", date=" + date + ", idUser="
				+ idUser + "]";
	}

}