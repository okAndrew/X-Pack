package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class FilesTypesSize {

	@TableColumn("type")
	private String type;

	@TableColumn("size")
	private double size;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("type: ").append(type).append(" size: ").append(size);
		return builder.toString();
	}
}
