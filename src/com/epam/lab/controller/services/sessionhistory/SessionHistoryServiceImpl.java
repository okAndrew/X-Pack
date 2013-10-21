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
	public SessionHistory addSession(long userid, Timestamp startDate) {
		SessionHistory sessionhistory = new SessionHistory().setUserid(userid)
				.setStartdate(startDate);

		dao.insert(sessionhistory);
		return sessionhistory;
	}

	public SessionHistory getSessionHistByUserIDAndEndDate(long userId) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		SessionHistory sessionhistory = sessdaoimpl
				.getSessionHistByUserIDAndEndDate(userId);
		return sessionhistory;
	}

}