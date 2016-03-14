package com.grigorovartem.servletstudy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.*;

/**
 * Created by User on 09.03.16.
 */

public class Url {
    private static String url = "https://2ch.hk/vg/1.json";
    //private static String S;


    public static void urlCopier() throws IOException {

        HttpClient httpClient = HttpClients.createDefault(); //Создаем объект класса
        HttpGet httpGet = new HttpGet(url);// загружаем
        HttpResponse response = httpClient.execute(httpGet); // получаем страницу

        HttpEntity entity = response.getEntity(); // входящие данные

        //if (entity != null) {

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()))) {

                //File newFile = new File("D:\\Java\\" + System.currentTimeMillis() + ".txt");
                //newFile.createNewFile();
                //FileWriter writer = new FileWriter(newFile);
                String s = reader.readLine();
                while (s != null) {
                    System.out.println("Hello!");
                    File newFile = new File("D:\\Java\\" + System.currentTimeMillis() + ".txt");
                    FileWriter writer = new FileWriter(newFile);
                    writer.write(s);
                    Thread.sleep(300000);
                }

            } catch (RuntimeException | InterruptedException ex) {
                httpGet.abort();
            }

        }

    }
//}
