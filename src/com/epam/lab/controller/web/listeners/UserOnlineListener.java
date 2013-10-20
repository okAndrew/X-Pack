package com.epam.lab.controller.web.listeners;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionActivationListener, HttpSessionAttributeListener,
		ServletContextListener {
	private List<String> sessions = new ArrayList<String>();
	private static final Logger logger = Logger
			.getLogger(UserOnlineListener.class);
	private static int activeSessions = 0;
	HttpSession session1 = null;

	public void sessionCreated(HttpSessionEvent event) {
		activeSessions++;
		session1 = event.getSession();
		sessions.add(session1.getId());
		session1.setAttribute("counter", this);
//		ConcurrentMap<ServletContext, Set<HttpSession>> instance = (ConcurrentMap<ServletContext, Set<HttpSession>>) SessionMap
//				.getInstance();
//		synchronized (instance) {
//			ServletContext c = event.getSession().getServletContext();
//			Set<HttpSession> set = instance.get(c);
//			if (c == null) {
//				set = new HashSet<HttpSession>();
//				instance.put(c, set);
//			}
//			set.add(event.getSession());
//		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		if (activeSessions > 0)
			activeSessions--;
		session1 = event.getSession();
		sessions.remove(session1.getId());
		session1.setAttribute("counter", this);
//		ConcurrentMap<ServletContext, Set<HttpSession>> instance = (ConcurrentMap<ServletContext, Set<HttpSession>>) SessionMap
//				.getInstance();
//		synchronized (instance) {
//			ServletContext c = event.getSession().getServletContext();
//			Set<HttpSession> set = instance.get(c);
//			if (c != null) {
//				set.remove(event.getSession());
//			}
//		}
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent event) {

		logger.info("sessionDidActivate");
	}

	public int getActiveSessionNumber() {
		return activeSessions;
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent event) {

		logger.info("sessionDidPassivate");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// HttpSession session = event.getSession();
		// SessionHistoryService historyService = new SessionHistoryService();
		// if (session.getAttribute("userid") != null) {
		//
		// historyService.addSession(session.getId(),
		// (long) session.getAttribute("userid"),
		// // (long) session.getAttribute("adminid"),
		// CurrentTimeStamp.getCurrentTimeStamp());
		// }
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// HttpSession session = event.getSession();
		// SessionHistoryService historyService = new SessionHistoryService();
		// SessionHistory sessionhistory = new SessionHistory();
		// sessionhistory.setEnddate(CurrentTimeStamp.getCurrentTimeStamp());
		// historyService.update(sessionhistory);

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
//		session1.invalidate();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
