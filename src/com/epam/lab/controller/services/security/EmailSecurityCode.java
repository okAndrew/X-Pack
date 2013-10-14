package com.epam.lab.controller.services.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import com.epam.lab.model.User;

public class EmailSecurityCode {

	public static String getCode(User user) {
		Calendar calendar = Calendar.getInstance();
		MessageDigest md = null;

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String email = user.getEmail();
		String pass = user.getPassword();

		StringBuilder code = new StringBuilder();
		code.append(email).append(pass).append(year).append(month).append(day);

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] hash = md.digest(code.toString().getBytes()); 

		return hash.toString();
	}

}
