package com.epam.lab.controller.web.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.statistics.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.SessionHistory;

@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private static final Logger logger = Logger
			.getLogger(UserOnlineListener.class);
	private static int activeSessionsLogged = 0;
	private static int activeSessions = 0;
	SessionHistory sessionhistory = null;
	String browserLocalevalue = null;

	public void sessionCreated(HttpSessionEvent event) {
		activeSessions++;
		HttpSession session = event.getSession();
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		sessionhistory = historyService.insertSessionWithoutUser(
				session.getId(), TimeStampManager.getFormatCurrentTimeStamp());
		session.setAttribute("sessLocale", "");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		if (activeSessions > 0) {
			activeSessions--;
		}
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		HttpSession session = event.getSession();
		sessionhistory = historyService.getSessionHistBySessIdTomcat(session
				.getId());
		sessionhistory.setEnddate(TimeStampManager.getFormatCurrentTimeStamp());
		historyService.update(sessionhistory);
	}

	public static int getActiveSessionNumberLogged() {
		return activeSessionsLogged;
	}

	public static int getActiveSessionNumber() {
		return activeSessions;
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {

		HttpSession session = event.getSession();
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		if (event.getName().equals("userid")
				&& session.getAttribute("userid") != null) {
			activeSessionsLogged++;
			sessionhistory = historyService
					.getSessionHistBySessIdTomcat(session.getId());
			sessionhistory.setUserid((long) session.getAttribute("userid"));
			historyService.setUserId(sessionhistory);
		}

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (event.getName().equals("userid")) {
			if (activeSessionsLogged > 0) {
				activeSessionsLogged--;
			}
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {

	}
}
