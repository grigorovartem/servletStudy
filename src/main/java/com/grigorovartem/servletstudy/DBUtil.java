package com.grigorovartem.servletstudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil
{
   private static final String url = "jdbc:mysql://localhost:3306/video";
   private static final String user = "root";
   private static final String password = "admin";

   private static Connection con;

   private DBUtil()
   {

   }

   public static java.sql.Connection getInstance()
   {
      if (con == null)
      {
         try
         {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(url, user, password);

         }
         catch (SQLException | ClassNotFoundException e)
         {

            e.printStackTrace();
         }
      }
      return con;
   }
}
