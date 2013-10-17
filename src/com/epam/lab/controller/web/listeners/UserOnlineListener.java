package com.epam.lab.controller.web.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;


@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionActivationListener {
	private List<String> sessions = new ArrayList<String>();
	private static final Logger logger = Logger
			.getLogger(UserOnlineListener.class);

	public UserOnlineListener() {
	}

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.add(session.getId());

		session.setAttribute("counter", this);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId());
		session.setAttribute("counter", this);
	}

	public int getActiveSessionNumber() {
		return sessions.size();
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		logger.info("sessionDidActivate");
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		logger.info("sessionDidPassivate");
	}

}
