package com.epam.lab.controller.exceptions.notfound;

public class FolderNotFoundException extends Exception {
	private static final long serialVersionUID = 8057940704435341734L;

	public FolderNotFoundException() {
		super();
	}

	public FolderNotFoundException(String mes) {
		super(mes);
	}

}
