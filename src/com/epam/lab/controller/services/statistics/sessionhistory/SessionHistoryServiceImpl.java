package com.epam.lab.controller.services.statistics.sessionhistory;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.sessionhistory.SessionHistoryDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryServiceImpl extends
		AbstractServiceImpl<SessionHistory> implements SessionHistoryService {

	public SessionHistoryServiceImpl() {
		super(new SessionHistoryDAOImpl());
	}

	public SessionHistory addSession(long userid, Timestamp startDate,
			String sessId) {
		SessionHistory sessionhistory = new SessionHistory().setUserid(userid)
				.setStartdate(startDate).setSessIdTomcat(sessId);
		dao.insert(sessionhistory);
		return sessionhistory;
	}

	public SessionHistory insertSessionWithoutUser(String sessIdTomcat,
			Timestamp startDate) {
		SessionHistory sessionhistory = new SessionHistory().setStartdate(
				startDate).setSessIdTomcat(sessIdTomcat);
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		sessdaoimpl.insertWithoutUser(sessionhistory);
		return sessionhistory;
	}

	public SessionHistory getSessionHistBySessIdTomcat(String sessId) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		return sessdaoimpl
				.getSessionHistBySessIdTomcat(sessId);
	}

	public int setUserId(SessionHistory sessionHistory) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		return sessdaoimpl.setUserId(sessionHistory);
	}

	public List<SessionHistory> getLoggedVisitorsByDate(Timestamp startDate,
			Timestamp endtDate) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		List<SessionHistory> sessionhistory = sessdaoimpl
				.getLoggedVisitorsByDate(startDate, endtDate);
		return sessionhistory;
	}

	public List<SessionHistory> getAllVisitorsByDate(Timestamp startDate,
			Timestamp endtDate) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		List<SessionHistory> sessionhistory = sessdaoimpl.getAllVisitorsByDate(
				startDate, endtDate);
		return sessionhistory;
	}

}