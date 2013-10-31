package com.epam.lab.controller.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements Runnable {
	
	String mailTo = null;
	String mailSub = null;
	String mailMsg = null;

	public void send(String to, String sub, String msg) {
		mailTo = to;
		mailSub = sub;
		mailMsg = msg;
		
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		Properties properties = new Properties();

		final String username = "dreamh.mail@gmail.com";
		final String password = "dreamhostbaby";

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mailTo));
			message.setSubject(mailSub);
			message.setText(mailMsg);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
