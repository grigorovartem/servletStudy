package com.grigorovartem.servletstudy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserJson {

    public List<Webm> saveWebm(InputStream stream) {

        JSONParser parser = new JSONParser();
        List<Webm> webms = new ArrayList<>();

        try {

            Object obj = parser.parse(new BufferedReader(new InputStreamReader(stream)));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray threadsArray = (JSONArray) jsonObject.get("threads");
            for (Object i : threadsArray) {
                JSONObject threadsObject = (JSONObject) i;
                JSONArray postsArray = (JSONArray) threadsObject.get("posts");
                for (Object j : postsArray) {
                    JSONObject postsObject = (JSONObject) j;
                    JSONArray filesArray = (JSONArray) postsObject.get("files");
                    for (Object m : filesArray) {
                        JSONObject webmObject = (JSONObject) m;
                        Webm web = new Webm();
                        web.setDuration((String) webmObject.get("duration"));
                        web.setHeight((Long) webmObject.get("height"));
                        web.setWidth((Long) webmObject.get("width"));
                        web.setName((String) webmObject.get("name"));
                        web.setNsfw((Long) webmObject.get("nsfw"));
                        web.setSize((Long) webmObject.get("size"));
                        web.setPath((String) webmObject.get("path"));
                        web.setThumbnail((String) webmObject.get("thumbnail"));
                        web.setTn_height((Long) webmObject.get("tn_height"));
                        web.setTn_width((Long) webmObject.get("tn_width"));

                        if (web.getName().endsWith(".webm")) {
                            webms.add(web);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return webms;

    }
}
