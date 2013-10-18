package com.epam.lab.controller.web.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.SessionHistoryService;
import com.epam.lab.controller.utils.CurrentTimeStamp;
import com.epam.lab.model.SessionHistory;

@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionActivationListener, HttpSessionAttributeListener {
	private List<String> sessions = new ArrayList<String>();
	private static final Logger logger = Logger
			.getLogger(UserOnlineListener.class);

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

	@Override
	public void sessionDidActivate(HttpSessionEvent event) {

		logger.info("sessionDidActivate");
	}

	public int getActiveSessionNumber() {
		return sessions.size();
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent event) {

		logger.info("sessionDidPassivate");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
//		HttpSession session = event.getSession();
//		SessionHistoryService historyService = new SessionHistoryService();
//		if (session.getAttribute("userid") != null) {
//
//			historyService.addSession(session.getId(),
//					(long) session.getAttribute("userid"),
//					// (long) session.getAttribute("adminid"),
//					CurrentTimeStamp.getCurrentTimeStamp());
//		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
//		HttpSession session = event.getSession();
//		SessionHistory sessionhistory = new SessionHistory();
//		SessionHistoryService historyService = new SessionHistoryService();
//		sessionhistory.setSessid(session.getId()).setEnddate(
//				CurrentTimeStamp.getCurrentTimeStamp());
//		historyService.update(sessionhistory);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

}
