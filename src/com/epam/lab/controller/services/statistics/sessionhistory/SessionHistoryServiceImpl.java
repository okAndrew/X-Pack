package com.epam.lab.controller.services.statistics.sessionhistory;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.sessionhistory.SessionHistoryDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryServiceImpl extends
		AbstractServiceImpl<SessionHistory> implements SessionHistoryService {
	private SessionHistoryDAOImpl sessionHistoryDAOImpl = (SessionHistoryDAOImpl) dao;
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
		sessionHistoryDAOImpl.insertWithoutUser(sessionhistory);
		return sessionhistory;
	}

	public SessionHistory getSessionHistBySessIdTomcat(String sessId) {
		return sessionHistoryDAOImpl.getSessionHistBySessIdTomcat(sessId);
	}

	public int setUserId(SessionHistory sessionHistory) {
		return sessionHistoryDAOImpl.setUserId(sessionHistory);
	}

	public List<SessionHistory> getLoggedVisitorsByDate(Timestamp startDate,
			Timestamp endtDate) {
		List<SessionHistory> sessionhistory = sessionHistoryDAOImpl
				.getLoggedVisitorsByDate(startDate, endtDate);
		return sessionhistory;
	}

	public List<SessionHistory> getAllVisitorsByDate(Timestamp startDate,
			Timestamp endtDate) {
		List<SessionHistory> sessionhistory = sessionHistoryDAOImpl.getAllVisitorsByDate(
				startDate, endtDate);
		return sessionhistory;
	}

}