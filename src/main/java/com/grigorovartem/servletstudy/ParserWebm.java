package com.grigorovartem.servletstudy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParserWebm implements Runnable
{
   private static final String URL = "https://2ch.hk/vg/1.json";
   private static final String FILE_DIRECTORY = "D:\\Java\\";
   private static final int DELAY_SECONDS = 60;

   private HttpClient httpClient = HttpClients.createDefault();
   private HttpGet httpGet = new HttpGet(URL);

   public void saveHtml() throws IOException
   {

      HttpResponse response = httpClient.execute(httpGet); // получаем страницу
      HttpEntity httpEntity = response.getEntity(); // входящие данные
      try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            FileWriter writer = new FileWriter(new File(generateFileName()))
      )
      {
         String line;
         while ((line = reader.readLine()) != null)
         {
            writer.write(line);
         }
         writer.flush();
      }
   }

   private String generateFileName()
   {
      return FILE_DIRECTORY + System.currentTimeMillis() + ".txt";
   }

   @Override
   public void run()
   {
      while (!Thread.interrupted())
      {
         try
         {
            System.out.println(System.currentTimeMillis() + ": Trying to save html!");
            saveHtml();
            Thread.sleep(DELAY_SECONDS * 1000);
         }
         catch (IOException | InterruptedException e)
         {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return;
         }
      }
   }
}