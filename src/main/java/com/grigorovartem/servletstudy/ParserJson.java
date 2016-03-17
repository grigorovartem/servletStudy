package com.grigorovartem.servletstudy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class ParserJson {
    private static final String FILENAME = "D:\\Java\\1458242112093.txt";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        Webm web = new Webm();

        try {

            Object obj = parser.parse(new FileReader(FILENAME));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray threadsArray = (JSONArray) jsonObject.get("threads");
            for (Object i : threadsArray)
            {
              JSONObject threadsObject = (JSONObject) i;
                JSONArray postsArray = (JSONArray)threadsObject.get("posts");
                for (Object j : postsArray)
                {
                    JSONObject postsObject = (JSONObject) j;
                    JSONArray filesArray = (JSONArray)postsObject.get("files");
                    for(Object m : filesArray)
                    {
                        JSONObject webmObject = (JSONObject) m;
                        web.setDuration((String)webmObject.get("duration"));
                        web.setHeight((Long) webmObject.get("height"));
                        web.setWidth((Long) webmObject.get("width"));
                        web.setName((String)webmObject.get("name"));
                        web.setNsfw((Long) webmObject.get("nsfw"));
                        web.setSize((Long) webmObject.get("size"));
                        web.setPath((String)webmObject.get("path"));
                        web.setThumbnail((String)webmObject.get("thumbnail"));
                        web.setTn_height((Long) webmObject.get("tn_height"));
                        web.setTn_width((Long) webmObject.get("tn_width"));

                        System.out.println(web.getDuration());
                        System.out.println(web.getHeight());
                        System.out.println(web.getWidth());
                        System.out.println(web.getName());
                        System.out.println(web.getNsfw());
                        System.out.println(web.getSize());
                        System.out.println(web.getPath());
                        System.out.println(web.getThumbnail());
                        System.out.println(web.getTn_height());
                        System.out.println(web.getTn_width());

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
