package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Locale {

	@TableColumn("id")
	private long id;

	@TableColumn("locale")
	private String locale;

	@TableColumn("language")
	private String language;

	public Locale() {
	}

	public Locale(long id, String locale, String language) {
		this.id = id;
		this.locale = locale;
		this.language = language;
	}

	public long getId() {
		return id;
	}

	public Locale setId(long id) {
		this.id = id;
		return this;
	}

	public String getLocale() {
		return locale;
	}

	public Locale setLocale(String locale) {
		this.locale = locale;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public Locale setLanguage(String language) {
		this.language = language;
		return this;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", locale=" + locale + ", language="
				+ language + "]";
	}

}