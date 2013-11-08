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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateEnd == null) ? 0 : dateEnd.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + (int) (tariff ^ (tariff >>> 32));
		result = prime * result + (int) (user ^ (user >>> 32));
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
		Payment other = (Payment) obj;
		if (available != other.available)
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateEnd == null) {
			if (other.dateEnd != null)
				return false;
		} else if (!dateEnd.equals(other.dateEnd))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (status != other.status)
			return false;
		if (tariff != other.tariff)
			return false;
		if (user != other.user)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user=" + user + ", tariff=" + tariff
				+ ", dateCreated=" + dateCreated + ", dateEnd=" + dateEnd
				+ ", price=" + price + ", status=" + status + ", available="
				+ available + "]";
	}
	
}