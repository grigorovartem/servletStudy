package com.grigorovartem.servletstudy;

import com.grigorovartem.servletstudy.validators.ValidationException;
import com.grigorovartem.servletstudy.validators.WebmValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet
{
   private static WebmDao dao = new WebmDao();
   private static WebmValidator webmValidator = new WebmValidator();

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      Webm web = extractWebm(req);
      PrintWriter out = resp.getWriter();
      try
      {
         webmValidator.validate(web);
         dao.save(web);
      }
      catch (ValidationException e)
      {
         e.printStackTrace();
      }
   }

   private Webm extractWebm(HttpServletRequest req)
   {
      Webm web = new Webm();
      web.setDuration(req.getParameter("duration"));
      web.setHeight(Integer.parseInt(req.getParameter("height")));
      web.setWidth(Integer.parseInt(req.getParameter("width")));
      web.setName(req.getParameter("name"));
      web.setNsfw(Integer.parseInt(req.getParameter("nsfw")));
      web.setSize(Integer.parseInt(req.getParameter("size")));
      web.setPath(req.getParameter("path"));
      web.setThumbnail(req.getParameter("thumbnail"));
      web.setTn_height(Integer.parseInt(req.getParameter("tn_height")));
      web.setTn_width(Integer.parseInt(req.getParameter("tn_width")));
      return web;
   }
}