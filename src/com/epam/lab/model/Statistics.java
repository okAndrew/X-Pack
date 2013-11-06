package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class Statistics {

	@TableColumn("number")
	private double number;

	@TableColumn("day")
	private Timestamp day;

	public Statistics() {
	}

	public Statistics(double number, Timestamp day) {
		super();
		this.number = number;
		this.day = day;
	}

	public Timestamp getDay() {
		return day;
	}

	public Statistics setDay(Timestamp day) {
		this.day = day;
		return this;
	}

	public double getNumber() {
		return number;
	}

	public Statistics setNumber(double number) {
		this.number = number;
		return this;
	}

	@Override
	public String toString() {
		return "Statistics [number=" + number + ", day=" + day + "]";
	}
}