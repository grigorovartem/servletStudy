package com.grigorovartem.servletstudy;

import com.grigorovartem.servletstudy.validators.ValidationException;
import com.grigorovartem.servletstudy.validators.WebmValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainServlet extends HttpServlet
{
   private static WebmDao dao = new WebmDao();
   private static WebmValidator webmValidator = new WebmValidator();
   ParserJson pj = new ParserJson();
    ArrayList<String> files = ParserWebm.getList();

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       ArrayList<String> files = ParserWebm.getList();
       for (String fileName : files) {
           Webm web = pj.saveWebm(fileName);//extractWebm(req);

           PrintWriter out = resp.getWriter();
           try {
               webmValidator.validate(web);

               dao.save(web);
           } catch (ValidationException e) {
               e.printStackTrace();
           }
       }
   }

   /*private Webm extractWebm(HttpServletRequest req)
   {
      Webm web = new Webm();
      web.setDuration(req.getParameter("duration"));
      web.setHeight(Long.parseLong(req.getParameter("height")));
      web.setWidth(Long.parseLong(req.getParameter("width")));
      web.setName(req.getParameter("name"));
      web.setNsfw(Long.parseLong(req.getParameter("nsfw")));
      web.setSize(Long.parseLong(req.getParameter("size")));
      web.setPath(req.getParameter("path"));
      web.setThumbnail(req.getParameter("thumbnail"));
      web.setTn_height(Long.parseLong(req.getParameter("tn_height")));
      web.setTn_width(Long.parseLong(req.getParameter("tn_width")));
      return web;
   }*/
}