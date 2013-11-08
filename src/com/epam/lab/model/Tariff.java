package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Tariff {
	@TableColumn("id")
	private long id;

	@TableColumn("name")
	private String name;

	@TableColumn("max_capacity")
	private long maxCapacity;

	@TableColumn("price")
	private double price;

	@TableColumn("description")
	private String description;

	@TableColumn("position")
	private int position;

	@TableColumn("is_delete")
	private boolean isDelete;

	public Tariff setPosition(int position) {
		this.position = position;
		return this;
	}

	public boolean getIsDelete() {
		return isDelete;
	}

	public Tariff setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
		return this;
	}

	public int getPosition() {
		return position;
	}

	public long getId() {
		return id;
	}

	public Tariff setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Tariff setName(String name) {
		this.name = name;
		return this;
	}

	public long getMaxCapacity() {
		return maxCapacity;
	}

	public Tariff setMaxCapacity(long maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}

	public double getPrice() {
		return price;
	}

	public Tariff setPrice(double price) {
		this.price = price;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Tariff setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tariff [id=").append(id).append(", name=").append(name)
				.append(", maxCapacity=").append(maxCapacity)
				.append(", price=").append(price).append(", description")
				.append(description).append(", position=").append(position)
				.append(", isDelete=").append(isDelete);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isDelete ? 1231 : 1237);
		result = prime * result + (int) (maxCapacity ^ (maxCapacity >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + position;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Tariff other = (Tariff) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (maxCapacity != other.maxCapacity)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position != other.position)
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}

}