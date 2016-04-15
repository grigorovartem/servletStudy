package com.grigorovartem.servletstudy.export.dvach;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.grigorovartem.servletstudy.app.Constants;
import com.grigorovartem.servletstudy.model.Webm;
import org.apache.commons.io.FileUtils;

public class Downloader
{
   private final ExecutorService executorService;

   public Downloader()
   {
      executorService = Executors.newFixedThreadPool(1);
   }

   public void addToQueue(final Webm web)
   {
      executorService.execute(new Runnable()
      {
         public void run()
         {
            System.out.println("Start download " + web.getName() + " file.");
            File destination = new File(Constants.PATH_TO_SAVE + web.getName());
            try
            {

               FileUtils.copyURLToFile(new URL(Constants.DOWLOAD_2CH_WEBM_PREFIX + web.getPath()), destination);
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
            finally
            {
               System.out.println("End download " + web.getName() + " file.");
            }
         }
      });

   }
}
