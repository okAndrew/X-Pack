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