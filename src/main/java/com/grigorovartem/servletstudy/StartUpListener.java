package com.grigorovartem.servletstudy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartUpListener implements ServletContextListener
{
   private Thread parsingThread;

   @Override
   public void contextDestroyed(ServletContextEvent arg0)
   {
      System.out.println("ServletContextListener destroyed");
      if (parsingThread != null)
      {
         parsingThread.interrupt();
      }
   }

   @Override
   public void contextInitialized(ServletContextEvent arg0)
   {
      System.out.println("StartUpListener started.");
      parsingThread = new Thread(new ParserWebm());
      parsingThread.start();
   }
}
