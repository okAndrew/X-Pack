package com.epam.lab.controller.services.sessionhistory;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.controller.dao.sessionhistory.SessionHistoryDAOImpl;
import com.epam.lab.controller.services.AbstractServiceImpl;
import com.epam.lab.model.SessionHistory;
import com.epam.lab.model.User;

public class SessionHistoryServiceImpl extends
		AbstractServiceImpl<SessionHistory> implements SessionHistoryService {

	public SessionHistoryServiceImpl() {
		super(new SessionHistoryDAOImpl());
	}

	static Logger logger = Logger.getLogger(SessionHistoryServiceImpl.class);

	// rename to insert(SessionHistory sessionHistory) !
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
		SessionHistory sessionhistory = sessdaoimpl
				.getSessionHistBySessIdTomcat(sessId);
		return sessionhistory;
	}

	public int setUserId(SessionHistory sessionHistory) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		int sessionhistory = sessdaoimpl.setUserId(sessionHistory);
		return sessionhistory;
	}

	public List<SessionHistory> getLoggedVisitorsByDate(Timestamp startDate, Timestamp endtDate) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		List<SessionHistory> sessionhistory = sessdaoimpl.getLoggedVisitorsByDate(startDate, endtDate);
		return sessionhistory;
	}
	
	public List<SessionHistory> getAllVisitorsByDate(Timestamp startDate, Timestamp endtDate) {
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		List<SessionHistory> sessionhistory = sessdaoimpl.getAllVisitorsByDate(startDate, endtDate);
		return sessionhistory;
	}
	
	public long getVisitsPerDayByUserId(long userId){
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		List<SessionHistory> sessionhistory = sessdaoimpl.getVisitsPerDayByUserId(userId);
		return sessionhistory.size();
		
	}
	public Timestamp getAvarageTimeSessionByUserId(long userId){
		SessionHistoryDAOImpl sessdaoimpl = new SessionHistoryDAOImpl();
		Timestamp avarageTime = sessdaoimpl.getAvarageTimeSessionByUserId(userId);
		return avarageTime;
	}

}