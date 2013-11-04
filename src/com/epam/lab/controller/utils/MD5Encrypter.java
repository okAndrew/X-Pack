package com.epam.lab.controller.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Encrypter {

	public String encrypt(String text) {
		String hashPass = null;

		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(text.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashPass = bigInt.toString(16);

			while (hashPass.length() < 32) {
				hashPass = "0" + hashPass;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		return hashPass;
	}

	public String generateRandomHash() {
		String text = TimeStampManager.getFormatCurrentTimeStamp().toString()
				+ new Random().nextLong();
		return encrypt(text);
	}
}
