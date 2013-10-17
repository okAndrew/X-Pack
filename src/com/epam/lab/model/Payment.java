package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class Payment {
	@TableColumn("id")
	private long id;

	@TableColumn("user")
	private long user;

	@TableColumn("tariff")
	private long tariff;

	@TableColumn("date_created")
	private Timestamp dateCreated;

	@TableColumn("date_end")
	private Timestamp dateEnd;
	
	@TableColumn("price")
	private double price;;
	
	@TableColumn("status")
	private boolean status;
	
	@TableColumn("available")
	private boolean available;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getTariff() {
		return tariff;
	}

	public void setTariff(long tariff) {
		this.tariff = tariff;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Timestamp dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}