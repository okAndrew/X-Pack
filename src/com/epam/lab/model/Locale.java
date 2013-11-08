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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
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
		Locale other = (Locale) obj;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (locale == null) {
			if (other.locale != null)
				return false;
		} else if (!locale.equals(other.locale))
			return false;
		return true;
	}

}