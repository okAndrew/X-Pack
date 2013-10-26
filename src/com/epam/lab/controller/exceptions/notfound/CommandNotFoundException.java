package com.epam.lab.controller.exceptions.notfound;

public class CommandNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(){
		super();
	}
	
	public CommandNotFoundException(String message){
		super(message);
	}
}
