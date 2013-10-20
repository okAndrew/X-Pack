package com.epam.lab.controller.services.sessionhistory;

import java.sql.Timestamp;
import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.sessionhistory.SessionHistoryDAOImpl;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryServiceImpl {

	static Logger logger = Logger.getLogger(SessionHistoryServiceImpl.class);

	public SessionHistory addSession(String sessId, long userid, Timestamp startDate) {
		SessionHistory sessionhistory = new SessionHistory().setSessid(sessId).setUserid(userid)
				.setStartdate(startDate);

		SessionHistoryDAOImpl sessionDAOImpl = new SessionHistoryDAOImpl();
		sessionDAOImpl.insert(sessionhistory);
		return sessionhistory;
	}

	public int update(SessionHistory sessionObject) {
		SessionHistoryDAOImpl sessionDAOImpl = new SessionHistoryDAOImpl();
		int result = sessionDAOImpl.update(sessionObject);
		return result;

	}

}