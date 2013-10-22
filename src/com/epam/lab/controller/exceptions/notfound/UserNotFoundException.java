package com.epam.lab.controller.exceptions.notfound;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = -4719013076394587519L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String mes) {
		super(mes);
	}

}
