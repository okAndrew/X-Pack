package com.epam.lab.controller.services;

import java.sql.Timestamp;
import org.apache.log4j.Logger;
import com.epam.lab.controller.dao.impl.SessionHistoryDAOImpl;
import com.epam.lab.model.SessionHistory;

public class SessionHistoryService {

	static Logger logger = Logger.getLogger(SessionHistoryService.class);

	public void addSession(long userid, Timestamp startDate) {
		SessionHistory session = new SessionHistory().setUserid(userid)
				.setStartdate(startDate);

		SessionHistoryDAOImpl sessionDAOImpl = new SessionHistoryDAOImpl();
		sessionDAOImpl.insert(session);

	}

	public int update(SessionHistory sessionObject) {
		SessionHistoryDAOImpl sessionDAOImpl = new SessionHistoryDAOImpl();
		int result = sessionDAOImpl.update(sessionObject);
		return result;

	}

}