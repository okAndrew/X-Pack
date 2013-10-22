package com.epam.lab.model;

import java.util.ArrayList;
import java.util.List;

public enum Role {
	USER(1, "user"), ADMIN(2, "admin");
	private static final List<Role> roles;
	static {
		roles = new ArrayList<Role>();
		roles.add(USER);
		roles.add(ADMIN);
	}

	private int number;
	private String name;

	private Role(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public static Role findByName(String roleName) {
		for (Role role : roles) {
			if (role.getName().equalsIgnoreCase(roleName)) {
				return role;
			}
		}
		return null;
	}

	public static Role findByNumber(int roleNumber) {
		for (Role role : roles) {
			if (role.getNumber() == roleNumber) {
				return role;
			}
		}
		return null;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}