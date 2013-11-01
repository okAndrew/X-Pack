package com.epam.lab.controller.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Validator {
	
	USER_LOGIN("[A-Za-z0-9]{4,16}$"),
	USER_EMAIL("^([0-9a-zA-Z]+[-._+&amp;])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$"),
	USER_PASSWORD("^[0-9A-Za-z]{1,32}$"),
	MD5_CHECKSUM("[a-fA-F0-9]{32}"),
	TARIFF_NAME("[A-Za-z]{3,15}$"),
	INTEGERS("[0-9]{9}"),
	DECIMALS("[0-9]{1,4}.[0-9]{0,2}");
	
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
