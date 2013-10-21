package com.epam.lab.controller.web.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.model.SessionHistory;

@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private Map<String, SessionHistory> sessions = new HashMap<String, SessionHistory>();
	private static final Logger logger = Logger
			.getLogger(UserOnlineListener.class);
	private static int activeSessions = 0;
	SessionHistory sessionhistory = null;

	public void sessionCreated(HttpSessionEvent event) {
		activeSessions++;

	}

	public void sessionDestroyed(HttpSessionEvent event) {
		if (activeSessions > 0)
			activeSessions--;
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		HttpSession session = event.getSession();
		sessionhistory = historyService
				.getSessionHistByUserIDAndEndDate((long) session
						.getAttribute("userid"));
		sessionhistory.setEnddate(CurrentTimeStamp.getCurrentTimeStamp());
		historyService.update(sessionhistory);
		sessions.remove(event.getSession().getId());
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

			sessionhistory = historyService.addSession(
					(long) session.getAttribute("userid"),
					CurrentTimeStamp.getCurrentTimeStamp());
			sessions.put(session.getId(), sessionhistory);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		//

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		//
	}

}
