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
	private int capacity;

	@TableColumn("is_activated")
	private boolean isActivated;

	@TableColumn("role")
	private int role;

	public User() {
	}

	public User(long id, String login, String email, String password,
			long idTariff, int capacity, boolean isActivated, Role role) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.idTariff = idTariff;
		this.capacity = capacity;
		this.isActivated = isActivated;
		this.role = role.getNumber();
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

	public int getCapacity() {
		return capacity;
	}

	public User setCapacity(int capacity) {
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
				+ ", role=" + role + "]";
	}

}