package com.grigorovartem.servletstudy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

/**
 * Created by User on 10.03.16.
 */
public class MyServletContextListener implements ServletContextListener
{
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0)  {
        System.out.println("ServletContextListener started");
        try
        {
            Url.urlCopier();
        }
        catch (IOException e){}
    }
}
