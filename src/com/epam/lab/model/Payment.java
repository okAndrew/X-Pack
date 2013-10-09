package com.epam.lab.model;

import java.util.Date;

import com.epam.lab.controller.annotations.TableColumn;

public class Payment {
	@TableColumn("id")
	private long id;

	@TableColumn("description")
	private String description;

	@TableColumn("status")
	private boolean status;

	@TableColumn("date")
	private Date date;

	@TableColumn("id_user")
	private long idUser;

	@TableColumn("avaliable")
	private boolean avaliable;

	public long getId() {
		return id;
	}

	public Payment setId(long id) {
		this.id = id;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Payment setDescription(String description) {
		this.description = description;
		return this;
	}

	public boolean isStatus() {
		return status;
	}

	public Payment setStatus(boolean status) {
		this.status = status;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public Payment setDate(Date date) {
		this.date = date;
		return this;
	}

	public long getIdUser() {
		return idUser;
	}

	public Payment setIdUser(long idUser) {
		this.idUser = idUser;
		return this;
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public Payment setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
		return this;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", description=" + description
				+ ", status=" + status + ", date=" + date + ", idUser="
				+ idUser + ", avaliable=" + avaliable + "]";
	}

}