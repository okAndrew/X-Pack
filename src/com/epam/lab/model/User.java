package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class User {

	@TableColumn("id")
	private long id;

	@TableColumn("login")
	private String login;

	@TableColumn("email")
	private String email;

	@TableColumn("password")
	private String password;

	@TableColumn("id_tariff")
	private long idTariff;

	@TableColumn("capacity")
	private long capacity;

	@TableColumn("is_activated")
	private boolean isActivated;

	@TableColumn("is_banned")
	private boolean isBanned;

	@TableColumn("id_role")
	private int role;

	@TableColumn("last_locale")
	private String lastLocale;

	public User() {
	}

	public User(long id, String login, String email, String password,
			long idTariff, int capacity, boolean isActivated, boolean isBaned,
			Role role, String lastLocale) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.idTariff = idTariff;
		this.capacity = capacity;
		this.isActivated = isActivated;
		this.role = role.getNumber();
		this.lastLocale = lastLocale;
	}

	public String getLogin() {
		return login;
	}

	public User setLogin(String login) {
		this.login = login;
		return this;
	}

	public long getId() {
		return id;
	}

	public User setId(long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public long getIdTariff() {
		return idTariff;
	}

	public User setIdTariff(long idTariff) {
		this.idTariff = idTariff;
		return this;
	}

	public long getCapacity() {
		return capacity;
	}

	public User setCapacity(long capacity) {
		this.capacity = capacity;
		return this;
	}

	public boolean getIsActivated() {
		return isActivated;
	}

	public User setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
		return this;
	}

	public boolean getIsBanned() {
		return isBanned;
	}

	public User setIsBanned(boolean isBanned) {
		this.isBanned = isBanned;
		return this;
	}

	public String getLastLocale() {
		return lastLocale;
	}

	public User setLastLanguage(String lastLocale) {
		this.lastLocale = lastLocale;
		return this;
	}

	// one crafty getter...
	public Role getRole() {
		return Role.findByNumber(role);
	}

	public User setRole(Role role) {
		this.role = role.getNumber();
		return this;
	}

	public User setRole(int role) {
		this.role = role;
		return this;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", email=" + email
				+ ", password=" + password + ", idTariff=" + idTariff
				+ ", capacity=" + capacity + ", isActivated=" + isActivated
				+ ", isBanned=" + isBanned + ", role=" + role + ", lastLocale="
				+ lastLocale + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (capacity ^ (capacity >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idTariff ^ (idTariff >>> 32));
		result = prime * result + (isActivated ? 1231 : 1237);
		result = prime * result + (isBanned ? 1231 : 1237);
		result = prime * result
				+ ((lastLocale == null) ? 0 : lastLocale.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + role;
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
		User other = (User) obj;
		if (capacity != other.capacity)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (idTariff != other.idTariff)
			return false;
		if (isActivated != other.isActivated)
			return false;
		if (isBanned != other.isBanned)
			return false;
		if (lastLocale == null) {
			if (other.lastLocale != null)
				return false;
		} else if (!lastLocale.equals(other.lastLocale))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

}