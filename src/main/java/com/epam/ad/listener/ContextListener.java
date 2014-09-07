package com.epam.ad.listener;

import com.epam.ad.action.ActionException;
import com.epam.ad.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class ContextListener implements ServletContextListener
        {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

        try {
            ConnectionPool.init();
        } catch (ActionException e) {

            e.getMessage();
        }

        ConnectionPool connectionPool=ConnectionPool.getInstance();
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
        ConnectionPool.dispose();
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------

}
