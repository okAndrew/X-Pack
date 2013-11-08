package com.epam.lab.controller.exceptions;

public class NoSupportedActionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSupportedActionException() {
		super();
	}

	public NoSupportedActionException(String message) {
		super(message);
	}
}
