package com.epam.lab.controller.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validator {
	
	USER_LOGIN("^[A-Za-z]{2,45}$"),
	USER_EMAIL("^([0-9a-zA-Z]+[-._+&amp;])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$"),
	USER_PASSWORD("/^.{6,32}$");
	
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
