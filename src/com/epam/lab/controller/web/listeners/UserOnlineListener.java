package com.epam.lab.controller.web.listeners;

import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.epam.lab.controller.services.language.LanguageServiceImpl;
import com.epam.lab.controller.services.statistics.sessionhistory.SessionHistoryServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Language;
import com.epam.lab.model.SessionHistory;
import com.epam.lab.model.User;

@WebListener
public class UserOnlineListener implements HttpSessionListener,
		HttpSessionAttributeListener {
	private static int activeSessionsLogged = 0;
	private static int activeSessions = 0;
	SessionHistory sessionhistory = null;
	String browserLocalevalue = null;

	public void sessionCreated(HttpSessionEvent event) {
		activeSessions++;
		HttpSession session = event.getSession();
		session.setAttribute("currbrowsLang", "");
		session.setAttribute("sessLocale", "");
		LanguageServiceImpl impl = new LanguageServiceImpl();
		List<Language> list = impl.getAll();
		session.setAttribute("languages", list);
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		sessionhistory = historyService.insertSessionWithoutUser(
				session.getId(), TimeStampManager.getFormatCurrentTimeStamp());
		
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
		UserServiceImpl ui = new UserServiceImpl();
		HttpSession session = event.getSession();
		SessionHistoryServiceImpl historyService = new SessionHistoryServiceImpl();
		if (event.getName().equals("userid")
				&& session.getAttribute("userid") != null) {
			activeSessionsLogged++;
			sessionhistory = historyService
					.getSessionHistBySessIdTomcat(session.getId());
			sessionhistory.setUserid((long) session.getAttribute("userid"));
			historyService.setUserId(sessionhistory);
			User user = ui.get((long) session.getAttribute("userid"));
			if (user.getLastLocale() != null) {
				session.setAttribute("sessLocale", user.getLastLocale());
			}
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
		HttpSession session = event.getSession();
		if (event.getName().equals("sessLocale")) {
			if (session.getAttribute("userid") != null) {
				UserServiceImpl ui = new UserServiceImpl();
				ui.setLastLocale(session.getAttribute("sessLocale").toString(),
						(long) session.getAttribute("userid"));
			}
			LanguageServiceImpl langImpl = new LanguageServiceImpl();
			session.setAttribute("currentLanguage", langImpl.getLang(session
					.getAttribute("sessLocale").toString()));

		}
		if (event.getName().equals("userid")) {
			if (activeSessionsLogged > 0) {
				activeSessionsLogged--;
			}
		}
	}
}
