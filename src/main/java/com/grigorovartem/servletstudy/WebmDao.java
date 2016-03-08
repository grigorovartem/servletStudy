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
         stmt.setInt(2, web.getHeight());
         stmt.setInt(3, web.getWidth());
         stmt.setString(4, web.getName());
         stmt.setInt(5, web.getNsfw());
         stmt.setInt(6, web.getSize());
         stmt.setString(7, web.getPath());
         stmt.setString(8, web.getThumbnail());
         stmt.setInt(9, web.getTn_height());
         stmt.setInt(10, web.getTn_width());

         // executing query
         stmt.executeUpdate();
      }
      catch (SQLException sqlEx)
      {
         sqlEx.printStackTrace();
      }
   }
}
