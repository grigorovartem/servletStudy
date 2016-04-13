package com.grigorovartem.servletstudy.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.grigorovartem.servletstudy.model.Webm;

public class WebmDao
{
   private static String DURATION = "duration";
   private static String HEIGHT = "height";
   private static String WIDTH = "width";
   private static String NAME = "name";
   private static String NSFW = "nsfw";
   private static String SIZE = "size";
   private static String PATH = "path";
   private static String THUMBNAIL = "thumbnail";
   private static String TN_HEIGHT = "tn_height";
   private static String TN_WIDTH = "tn_width";

   private static String INSERT_QUERY =
         "INSERT INTO video.webm(duration, height, width, name, nsfw, size, path, thumbnail, tn_height, tn_width) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
   private static String RETRIEVE_BY_NAME_QUERY = "SELECT * FROM webm WHERE name=?;";

   public static void save(Webm web)
   {
      try (PreparedStatement stmt = DBUtil.getInstance()
            .prepareStatement(INSERT_QUERY))
      {
         stmt.setString(1, web.getDuration());
         stmt.setLong(2, web.getHeight());
         stmt.setLong(3, web.getWidth());
         stmt.setString(4, web.getName());
         stmt.setLong(5, web.getNsfw());
         stmt.setLong(6, web.getSize());
         stmt.setString(7, web.getPath());
         stmt.setString(8, web.getThumbnail());
         stmt.setLong(9, web.getTnHeight());
         stmt.setLong(10, web.getTnWidth());
         stmt.executeUpdate();

      }
      catch (SQLException sqlEx)
      {
         sqlEx.printStackTrace();
      }
   }

   public static Webm getByName(String name)
   {
      try (PreparedStatement stmt = DBUtil.getInstance()
            .prepareStatement(RETRIEVE_BY_NAME_QUERY))
      {
         stmt.setString(1, name);
         return convertToWebm(stmt.executeQuery());
      }
      catch (SQLException sqlEx)
      {
         sqlEx.printStackTrace();
      }
      return null;
   }

   public static Webm convertToWebm(ResultSet rs) throws SQLException
   {
      if (!rs.next())
      {
         return null;
      }
      Webm web = new Webm();
      web.setDuration(rs.getString(DURATION));
      web.setHeight(rs.getLong(HEIGHT));
      web.setName(rs.getString(NAME));
      web.setNsfw(rs.getLong(NSFW));
      web.setPath(rs.getString(PATH));
      web.setSize(rs.getLong(SIZE));
      web.setThumbnail(rs.getString(THUMBNAIL));
      web.setTnHeight(rs.getLong(TN_HEIGHT));
      web.setTnWidth(rs.getLong(TN_WIDTH));
      web.setWidth(rs.getLong(WIDTH));
      return web;
   }
}
