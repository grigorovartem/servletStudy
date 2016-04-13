package com.grigorovartem.servletstudy.export.dvach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.grigorovartem.servletstudy.model.Webm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Webm2chExtractor
{
   public static List<Webm> extract(InputStream stream) throws IOException, ParseException
   {
      List<Webm> webms = new ArrayList<>();

      JSONObject jsonObject = (JSONObject) new JSONParser().parse(new BufferedReader(new InputStreamReader(stream)));
      JSONArray threadsArray = (JSONArray) jsonObject.get("threads");
      for (Object i : threadsArray)
      {
         JSONObject threadsObject = (JSONObject) i;
         JSONArray postsArray = (JSONArray) threadsObject.get("posts");
         for (Object j : postsArray)
         {
            JSONObject postsObject = (JSONObject) j;
            JSONArray filesArray = (JSONArray) postsObject.get("files");
            for (Object m : filesArray)
            {
               JSONObject webmObject = (JSONObject) m;
               webms.add(extractWebm(webmObject));
            }
         }
      }
      return webms;

   }

   private static Webm extractWebm(JSONObject webmObject)
   {
      Webm web = new Webm();
      web.setDuration((String) webmObject.get("duration"));
      web.setHeight((Long) webmObject.get("height"));
      web.setWidth((Long) webmObject.get("width"));
      web.setName((String) webmObject.get("name"));
      web.setNsfw((Long) webmObject.get("nsfw"));
      web.setSize((Long) webmObject.get("size"));
      web.setPath((String) webmObject.get("path"));
      web.setThumbnail((String) webmObject.get("thumbnail"));
      web.setTnHeight((Long) webmObject.get("tn_height"));
      web.setTnWidth((Long) webmObject.get("tn_width"));
      return web;
   }
}
