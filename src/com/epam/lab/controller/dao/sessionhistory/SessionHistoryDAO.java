package com.epam.lab.controller.dao.sessionhistory;

import java.sql.Timestamp;
import java.util.List;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.SessionHistory;

public interface SessionHistoryDAO extends GenericDAO<SessionHistory> {

	int insertWithoutUser(SessionHistory sessionObject);

	SessionHistory getSessionHistBySessIdTomcat(String sessId);

	int setUserId(SessionHistory sessionObject);

	List<SessionHistory> getLoggedVisitorsByDate(Timestamp startDate,
			Timestamp endtDate);

	List<SessionHistory> getAllVisitorsByDate(Timestamp startDate,
			Timestamp endtDate);
}
