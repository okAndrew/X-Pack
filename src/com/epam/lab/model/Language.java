package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Language {

	@TableColumn("id")
	private long id;

	@TableColumn("name")
	private String name;

	@TableColumn("defaultlocale")
	private String defaultLocale;

	@TableColumn("path_image")
	private String pathImage;

	public Language() {
	}

	public Language(long id, String name, String defaultLocale, String pathImage) {
		this.id = id;
		this.name = name;
		this.defaultLocale = defaultLocale;
		this.pathImage = pathImage;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultLocale == null) ? 0 : defaultLocale.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((pathImage == null) ? 0 : pathImage.hashCode());
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
		Language other = (Language) obj;
		if (defaultLocale == null) {
			if (other.defaultLocale != null)
				return false;
		} else if (!defaultLocale.equals(other.defaultLocale))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pathImage == null) {
			if (other.pathImage != null)
				return false;
		} else if (!pathImage.equals(other.pathImage))
			return false;
		return true;
	}

	public Language setId(long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Language setName(String name) {
		this.name = name;
		return this;
	}

	public String getDefaulLocale() {
		return defaultLocale;
	}

	public Language setDefaultLocale(String defaultLocale) {
		this.defaultLocale = defaultLocale;
		return this;
	}

	public String getPathImage() {
		return pathImage;
	}

	public Language setPathImage(String pathImage) {
		this.pathImage = pathImage;
		return this;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", name=" + name + ", defaultLocale="
				+ defaultLocale + ", pathImage=" + pathImage + "]";
	}

}