package com.epam.lab.controller.services.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validator {
	
	USER_LOGIN("^[A-Z][a-z]+{2,15}$"),
	USER_EMAIL("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"),
	USER_PASSWORD("^[\\w_-]{6,15}$");
	
	private String regex;
	
	private Validator(String regex) {
		this.regex = regex;
	}
	
	public boolean validate(String string) {
		boolean res = false;
		
		if (string != null) {
			Pattern pattern = Pattern.compile(this.regex);
	        Matcher matcher = pattern.matcher(string);
	        res = matcher.matches();
		}

        return res;
	}

}
