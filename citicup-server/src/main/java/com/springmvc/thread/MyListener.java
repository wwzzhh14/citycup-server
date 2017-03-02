package com.springmvc.thread;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by guhan on 16/9/8.
 */
public class MyListener implements ServletContextListener{


    private  DataGrabber myThread;
    public void contextInitialized(ServletContextEvent sce) {

        myThread = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()).getBean(DataGrabber.class);
        myThread.start(); 

    }

    public void contextDestroyed(ServletContextEvent sce) {
        if (myThread != null && myThread.isInterrupted()) {
            myThread.interrupt();
        }
    }
}
