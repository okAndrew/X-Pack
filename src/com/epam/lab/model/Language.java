package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Language {

	@TableColumn("id")
	private long id;

	@TableColumn("locale")
	private String locale;

	@TableColumn("name")
	private String name;

	public Language() {
	}

	public Language(long id, String locale, String name) {
		this.id = id;
		this.locale = locale;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public Language setId(long id) {
		this.id = id;
		return this;
	}

	public String getLocale() {
		return locale;
	}

	public Language setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	public String getName() {
		return name;
	}

	public Language setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", locale=" + locale + ", name=" + name
				+ "]";
	}

}