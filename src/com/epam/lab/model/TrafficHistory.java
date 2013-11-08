package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class TrafficHistory {

	@TableColumn("id")
	private long id;

	@TableColumn("user_id")
	private Long userId;

	@TableColumn("date")
	private Timestamp date;

	@TableColumn("size")
	private int size;

	public long getId() {
		return id;
	}

	public TrafficHistory setId(long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public TrafficHistory setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public TrafficHistory setSize(int size) {
		this.size = size;
		return this;
	}

	public Timestamp getDate() {
		return date;
	}

	public TrafficHistory setDate(Timestamp date) {
		this.date = date;
		return this;
	}

	public int getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + size;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		TrafficHistory other = (TrafficHistory) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (size != other.size)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrafficHistory [id=" + id + ", userId=" + userId + ", date="
				+ date + ", size=" + size + "]";
	}

}
