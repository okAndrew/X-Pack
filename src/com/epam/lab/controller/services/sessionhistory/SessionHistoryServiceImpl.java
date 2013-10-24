package com.epam.lab.controller.services.sessionhistory;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.sessionhistory.SessionHistoryDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryServiceImpl extends
		AbstractServiceImpl<SessionHistory> implements SessionHistoryService {

	public SessionHistoryServiceImpl() {
		super(new SessionHistoryDAOImpl());
	}

	static Logger logger = Logger.getLogger(SessionHistoryServiceImpl.class);

	// rename to insert(SessionHistory sessionHistory) !
	public SessionHistory addSession(long userid, Timestamp startDate,  String sessId) {
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

	public SessionHistory getSessionHistByUserIDAndEndDate(long userId) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		SessionHistory sessionhistory = sessdaoimpl
				.getSessionHistByUserIDAndEndDate(userId);
		return sessionhistory;
	}
	public SessionHistory getSessionHistBySessIdTomcat(String sessId) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		SessionHistory sessionhistory = sessdaoimpl
				.getSessionHistBySessIdTomcat(sessId);
		return sessionhistory;
	}
	public int setUserId(SessionHistory sessionHistory) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		int sessionhistory = sessdaoimpl.setUserId(sessionHistory);
		return sessionhistory;
	}

}