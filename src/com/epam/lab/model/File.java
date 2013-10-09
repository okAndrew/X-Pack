package com.epam.lab.model;

import java.util.Date;

import com.epam.lab.controller.annotations.TableColumn;

public class File {
	@TableColumn("id")
	private long id;

	@TableColumn("folder")
	private long folder;

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
	private Date date;

	@TableColumn("id_user")
	private long idUser;

	public long getId() {
		return id;
	}

	public File setId(long id) {
		this.id = id;
		return this;
	}

	public long getFolder() {
		return folder;
	}

	public File setFolder(long folder) {
		this.folder = folder;
		return this;
	}

	public String getNameIncome() {
		return nameIncome;
	}

	public File setNameIncome(String nameIncome) {
		this.nameIncome = nameIncome;
		return this;
	}

	public String getName() {
		return name;
	}

	public File setName(String name) {
		this.name = name;
		return this;
	}

	public String getPath() {
		return path;
	}

	public File setPath(String path) {
		this.path = path;
		return this;
	}

	public String getType() {
		return type;
	}

	public File setType(String type) {
		this.type = type;
		return this;
	}

	public double getSize() {
		return size;
	}

	public File setSize(double size) {
		this.size = size;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public File setDate(Date date) {
		this.date = date;
		return this;
	}

	public long getIdUser() {
		return idUser;
	}

	public File setIdUser(long idUser) {
		this.idUser = idUser;
		return this;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", folder=" + folder + ", nameIncome="
				+ nameIncome + ", name=" + name + ", path=" + path + ", type="
				+ type + ", size=" + size + ", date=" + date + ", idUser="
				+ idUser + "]";
	}

}