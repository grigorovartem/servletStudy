package com.grigorovartem.servletstudy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadPool {

    private static final String DIRECTORY = "D:\\Java\\";
    private static final String URL = "https://2ch.hk/vg/";

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    public void addToQueue(final Webm web) {

        executorService.execute(new Runnable() {

                                    public void run() {
                                        System.out.println("Downloading started!");

                                        File destination = new File(DIRECTORY + web.getName());
                                        try {
                                            FileUtils.copyURLToFile(new URL(URL + web.getPath()), destination);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } finally {
                                            System.out.println("Downloading ended!");
                                        }
                                    }
                                });
    }
}


