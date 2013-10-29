package com.epam.lab.controller.web.servlets.security;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.user.UserServiceImpl;

public class AppServletContextListener implements ServletContextListener {
	
	private ScheduledExecutorService scheduler;
	static Logger logger = Logger.getLogger(AppServletContextListener.class);

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
	}
	
	private class setUsersForFree implements Runnable {
	    public void run() {
	    	logger.debug("setUsersForFree");
	    	new UserServiceImpl().setUsersForFree();
	    }
	}
	
	private class deactivateOverdues implements Runnable {
	    public void run() {
	    	logger.debug("deactivateOverdues");
	    	new UserServiceImpl().deactivateOverdue();
	    }
	}
	
}
