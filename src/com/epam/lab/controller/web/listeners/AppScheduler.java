package com.epam.lab.controller.web.listeners;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.file.UserFileService;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.token4auth.Token4AuthServiceImpl;
import com.epam.lab.controller.services.user.UserServiceImpl;

@WebListener()
public class AppScheduler implements ServletContextListener {

	private ScheduledExecutorService scheduler;
	static Logger logger = Logger.getLogger(AppScheduler.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleWithFixedDelay(new setUsersForFree(), 0, 5, TimeUnit.MINUTES);
		scheduler.scheduleWithFixedDelay(new deactivateOverdues(), 0, 15, TimeUnit.MINUTES);
		scheduler.scheduleWithFixedDelay(new deleteNoActiveTokens4Auth(), 0, 60, TimeUnit.MINUTES);
		scheduler.scheduleWithFixedDelay(new cleanTempDirectory(), 0, 1, TimeUnit.DAYS);
	}

	private class setUsersForFree implements Runnable {
		public void run() {
			new UserServiceImpl().setUsersForFree();
			logger.debug("setUsersForFree");
		}
	}

	private class deactivateOverdues implements Runnable {
		public void run() {
			logger.debug("deactivateOverdues");
			new UserServiceImpl().deactivateOverdue();
		}
	}

	private class deleteNoActiveTokens4Auth implements Runnable {
		public void run() {
			int numDeletedTokens = new Token4AuthServiceImpl().deleteNotActiveTokens();
			logger.debug("Deleted no active tokens for authentication: " + numDeletedTokens);
		}
	}

	private class cleanTempDirectory implements Runnable {
		public void run() {
			UserFileService service = new UserFileServiceImpl();
			service.cleanTempDirectory();
			logger.debug("Clean temp directory");
		}
	}
}

