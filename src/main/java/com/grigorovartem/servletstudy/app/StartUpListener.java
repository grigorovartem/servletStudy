package com.grigorovartem.servletstudy.app;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class StartUpListener implements ServletContextListener
{
   private Thread DvachExporterThread;

   @Override
   public void contextDestroyed(ServletContextEvent arg0)
   {
      System.out.println("ServletContextListener destroyed");
      // if (DvachExporterThread != null)
      // {
      // DvachExporterThread.interrupt();
      // }

   }

   @Override
   public void contextInitialized(ServletContextEvent arg0)
   {
      System.out.println("StartUpListener started.");
      // DvachExporterThread = new Thread(new Exporter());
      // DvachExporterThread.start();
   }

   public static void main(String[] args)
   {

   }
}
