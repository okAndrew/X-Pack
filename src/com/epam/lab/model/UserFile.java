package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;
import com.google.gson.annotations.Expose;

public class UserFile implements Comparable<UserFile> {
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
				+ idUser + ", isPublic=" + isPublic + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idFolder == null) ? 0 : idFolder.hashCode());
		result = prime * result + (int) (idUser ^ (idUser >>> 32));
		result = prime * result
				+ ((isPublic == null) ? 0 : isPublic.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nameIncome == null) ? 0 : nameIncome.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		UserFile other = (UserFile) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idFolder == null) {
			if (other.idFolder != null)
				return false;
		} else if (!idFolder.equals(other.idFolder))
			return false;
		if (idUser != other.idUser)
			return false;
		if (isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!isPublic.equals(other.isPublic))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameIncome == null) {
			if (other.nameIncome != null)
				return false;
		} else if (!nameIncome.equals(other.nameIncome))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (size != other.size)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public int compareTo(UserFile file) {
		if (this.getType().compareTo(file.getType()) != 0) {
			return this.getType().compareTo(file.getType());
		} else if (this.getNameIncome().compareTo(file.getNameIncome()) != 0) {
			return this.getNameIncome().compareTo(file.getNameIncome());
		} else if (this.getDate().compareTo(file.getDate()) != 0) {
			return this.getDate().compareTo(file.getDate());
		} else {
			if (this.getSize() - file.getSize() < 0) {
				return -1;
			} else if (this.getSize() - file.getSize() > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}

}