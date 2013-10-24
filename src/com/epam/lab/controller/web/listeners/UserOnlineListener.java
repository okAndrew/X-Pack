package com.epam.lab.controller.web.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.SessionHistory;

@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private static final Logger logger = Logger
			.getLogger(UserOnlineListener.class);
	private static int activeSessions = 0;
	SessionHistory sessionhistory = null;

	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		HttpSession session = event.getSession();
		if (session.getAttribute("userid") != null) {
			sessionhistory = historyService
					.getSessionHistByUserIDAndEndDate((long) session
							.getAttribute("userid"));
			sessionhistory.setEnddate(TimeStampManager
					.getFormatCurrentTimeStamp());
			historyService.update(sessionhistory);
		}
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
			activeSessions++;
			sessionhistory = historyService.addSession(
					(long) session.getAttribute("userid"),
					TimeStampManager.getFormatCurrentTimeStamp());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (event.getName().equals("userid")) {
			if (activeSessions > 0)
				activeSessions--;
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		//
	}

}
