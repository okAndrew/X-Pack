package com.epam.lab.controller.exceptions.notfound;

public class TokenNotFoundException extends Exception {
	private static final long serialVersionUID = 1431320067288232120L;

	public TokenNotFoundException() {
		super();
	}

	public TokenNotFoundException(String mes) {
		super(mes);
	}

}
