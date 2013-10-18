package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Tariff {
	@TableColumn("id")
	private long id;

	@TableColumn("name")
	private String name;

	@TableColumn("max_capacity")
	private int maxCapacity;

	@TableColumn("price")
	private double price;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@TableColumn("description")
	private String description;

	@TableColumn("available")
	private boolean available;

	@TableColumn("position")
	private int position;

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

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public Tariff setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
		return this;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Tariff [id=" + id + ", name=" + name + ", maxCapacity="
				+ maxCapacity + "]";
	}

}