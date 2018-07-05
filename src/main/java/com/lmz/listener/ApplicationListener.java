package com.lmz.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
public class ApplicationListener implements ServletContextListener {
	
    /**
     * Default constructor. 
     */
    public ApplicationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext applicationContext=sce.getServletContext();
    	applicationContext.setAttribute("root", applicationContext.getContextPath());
    }
	
}
