package com.grigorovartem.servletstudy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserWebm implements Runnable {
    private static final String IPURL = "http://localhost:8080/serv-1.0-SNAPSHOT/";
    private static final String URL = "https://2ch.hk/vg/1.json";
    private static final int DELAY_SECONDS = 60;


    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private HttpGet httpGet = new HttpGet(URL);
    private HttpPost httpPost = new HttpPost(IPURL);
    DownloadPool dp = new DownloadPool();

    public void saveHtml() throws IOException {

        HttpResponse response2 = httpClient.execute(httpGet);
        HttpEntity httpEntity2 = response2.getEntity();
        ParserJson pj = new ParserJson();
        List<Webm> webmList = pj.saveWebm(httpEntity2.getContent());

        for (Webm webm : webmList) {

            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("duration", webm.getDuration()));
            nvps.add(new BasicNameValuePair("height", webm.getHeight().toString()));
            nvps.add(new BasicNameValuePair("width", webm.getWidth().toString()));
            nvps.add(new BasicNameValuePair("name", webm.getName()));
            nvps.add(new BasicNameValuePair("nsfw", webm.getNsfw().toString()));
            nvps.add(new BasicNameValuePair("size", webm.getSize().toString()));
            nvps.add(new BasicNameValuePair("path", webm.getPath()));
            nvps.add(new BasicNameValuePair("thumbnail", webm.getThumbnail()));
            nvps.add(new BasicNameValuePair("tn_height", webm.getTn_height().toString()));
            nvps.add(new BasicNameValuePair("tn_width", webm.getTn_width().toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            response.close();

            dp.addToQueue(webm);

        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity2.getContent()))) {}
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println(System.currentTimeMillis() + ": Trying to save html!");
                saveHtml();

                Thread.sleep(DELAY_SECONDS * 1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}