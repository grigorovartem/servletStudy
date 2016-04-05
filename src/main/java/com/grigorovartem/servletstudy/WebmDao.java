package com.grigorovartem.servletstudy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WebmDao
{
   private static String INSERT_QUERY =
         "INSERT INTO video.webm(duration, height, width, name, nsfw, size, path, thumbnail, tn_height, tn_width) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

   public void save(Webm web)
   {
      try (PreparedStatement stmt = DBUtil.getInstance().prepareStatement(INSERT_QUERY))
      {
         stmt.setString(1, web.getDuration());
         stmt.setLong(2, web.getHeight());
         stmt.setLong(3, web.getWidth());
         stmt.setString(4, web.getName());
         stmt.setLong(5, web.getNsfw());
         stmt.setLong(6, web.getSize());
         stmt.setString(7, web.getPath());
         stmt.setString(8, web.getThumbnail());
         stmt.setLong(9, web.getTn_height());
         stmt.setLong(10, web.getTn_width());

         // executing query
         stmt.executeUpdate();
         System.out.println(INSERT_QUERY);
      }
      catch (SQLException sqlEx)
      {
         sqlEx.printStackTrace();
      }
   }
   //System.out.println(INSERT_QUERY);

}
