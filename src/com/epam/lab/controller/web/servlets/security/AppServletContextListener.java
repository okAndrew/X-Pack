package com.epam.lab.controller.web.servlets.security;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.epam.lab.controller.services.payment.PaymentServiceImpl;

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
	    scheduler.scheduleAtFixedRate(new CleanDBTask(), 0, 5, TimeUnit.SECONDS);
	}
	
	public class CleanDBTask extends TimerTask {
	    public void run() {
	    	System.out.println("delete payment");
	    	new PaymentServiceImpl().deactivateOverdueTariff();
	    }
	}
	
}
