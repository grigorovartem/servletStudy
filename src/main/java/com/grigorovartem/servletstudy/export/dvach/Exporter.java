package com.grigorovartem.servletstudy.export.dvach;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.grigorovartem.servletstudy.app.Constants;
import com.grigorovartem.servletstudy.model.Webm;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.grigorovartem.servletstudy.dao.WebmDao;
import org.json.simple.parser.ParseException;

public class Exporter implements Runnable {
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private Downloader downloader = new Downloader();

    public void exportWebm() throws IOException, ParseException {
        System.out.println("Before process go");
        List<List<Webm>> webmsWebms = parseThreads();
        for (List<Webm> webms : webmsWebms)
            process(webms);
    }

    private void process(List<Webm> webms) throws IOException {
        for (Webm webm : webms) {
            if (isAlreadyExist(webm)) {
                continue;
            }
            System.out.println("Process GO!");
            HttpPost httpPost = new HttpPost(Constants.API_URL);
            httpPost.setEntity(createPostEntity(webm));
            httpClient.execute(httpPost)
                    .close();
            downloader.addToQueue(webm);
        }
    }

    private boolean isAlreadyExist(Webm webm) {
        return WebmDao.getByName(webm.getName()) != null;
    }

    private UrlEncodedFormEntity createPostEntity(Webm webm) throws UnsupportedEncodingException {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("duration", webm.getDuration()));
        parameters.add(new BasicNameValuePair("height", webm.getHeight()
                .toString()));
        parameters.add(new BasicNameValuePair("width", webm.getWidth()
                .toString()));
        parameters.add(new BasicNameValuePair("name", webm.getName()));
        parameters.add(new BasicNameValuePair("nsfw", webm.getNsfw()
                .toString()));
        parameters.add(new BasicNameValuePair("size", webm.getSize()
                .toString()));
        parameters.add(new BasicNameValuePair("path", webm.getPath()));
        parameters.add(new BasicNameValuePair("thumbnail", webm.getThumbnail()));
        parameters.add(new BasicNameValuePair("tn_height", webm.getTnHeight()
                .toString()));
        parameters.add(new BasicNameValuePair("tn_width", webm.getTnWidth()
                .toString()));
        return new UrlEncodedFormEntity(parameters);
    }

    private List<String> parseNums() throws IOException, ParseException {
        HttpGet httpGet = new HttpGet(Constants.JSON_2CH_API);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        List<String> nums = Webm2chExtractor.extractNums(response.getEntity()
                .getContent());
        response.close();
        return nums;
    }

    private List<List<Webm>> parseThreads() throws IOException, ParseException {
        List<String> numList = parseNums();
        List<List<Webm>> listOfWebms = new ArrayList<>();
        for (String l : numList) {
            HttpGet httpGet = new HttpGet(Constants.NUM_PREFIX + l + ".json");
            CloseableHttpResponse response = httpClient.execute(httpGet);

            listOfWebms.add(Webm2chExtractor.extractThreads(response.getEntity()
                    .getContent()));
            response.close();
        }

        System.out.println("Threads extracted!");
        return listOfWebms;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println(System.currentTimeMillis() + ": Trying to save html!");
                exportWebm();

                Thread.sleep(Constants.DELAY_SECONDS * 1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread()
                        .interrupt();
                return;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}