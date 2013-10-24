package com.epam.lab.controller.dao.sessionhistory;

import com.epam.lab.controller.dao.GenericDAO;
import com.epam.lab.model.SessionHistory;

public interface SessionHistoryDAO extends GenericDAO<SessionHistory> {
	SessionHistory getSessionHistByUserIDAndEndDate(long userId);

	int insertWithoutUser(SessionHistory sessionObject);

	SessionHistory getSessionHistBySessIdTomcat(String sessId);

	int setUserId(SessionHistory sessionObject);
}
