package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;
import com.google.gson.annotations.Expose;

public class UserFile {
	@Expose
	@TableColumn("id")
	private Long id;
	
	@Expose
	@TableColumn("id_folder")
	private Long idFolder;
	
	@Expose
	@TableColumn("name_income")
	private String nameIncome;

	@TableColumn("name")
	private String name;

	@TableColumn("path")
	private String path;

	@TableColumn("type")
	private String type;
	
	@Expose
	@TableColumn("size")
	private long size;

	@TableColumn("date")
	private Timestamp date;

	@TableColumn("id_user")
	private long idUser;
	
	@TableColumn("is_public")
	private Boolean isPublic;

	public Long getId() {
		return id;
	}

	public UserFile setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getIdFolder() {
		return idFolder;
	}

	public UserFile setIdFolder(Long idFolder) {
		this.idFolder = idFolder;
		return this;
	}

	public String getNameIncome() {
		return nameIncome;
	}

	public UserFile setNameIncome(String nameIncome) {
		this.nameIncome = nameIncome;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserFile setName(String name) {
		this.name = name;
		return this;
	}

	public String getPath() {
		return path;
	}

	public UserFile setPath(String path) {
		this.path = path;
		return this;
	}

	public String getType() {
		return type;
	}

	public UserFile setTypeByExtention(String extention) {
		this.type = FileType.findByExtention(extention).getName();
		return this;
	}

	public UserFile setType(FileType fileType) {
		this.type = fileType.getName();
		return this;
	}

	public long getSize() {
		return size;
	}

	public UserFile setSize(long size) {
		this.size = size;
		return this;
	}

	public Timestamp getDate() {
		return date;
	}

	public UserFile setDate(Timestamp date) {
		this.date = date;
		return this;
	}

	public long getIdUser() {
		return idUser;
	}

	public UserFile setIdUser(long idUser) {
		this.idUser = idUser;
		return this;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", idFolder=" + idFolder + ", nameIncome="
				+ nameIncome + ", name=" + name + ", path=" + path + ", type="
				+ type + ", size=" + size + ", date=" + date + ", idUser="
				+ idUser + "]";
	}

}