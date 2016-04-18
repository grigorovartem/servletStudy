package com.grigorovartem.servletstudy.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.grigorovartem.servletstudy.dao.WebmDao;
import com.grigorovartem.servletstudy.model.Webm;
import com.grigorovartem.servletstudy.validators.ValidationException;
import com.grigorovartem.servletstudy.validators.WebmValidator;

public class MainServlet extends HttpServlet
{

   private ApplicationContext ac = null;

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {

      // Webm web = extractWebm(req);
      Webm web = new Webm();
      try
      {
         WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
         ctx.getBean("WebmValidator", WebmValidator.class)
               .validate(web);
         ctx.getBean("WebmDao", WebmDao.class)
               .save(web);
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
      web.setHeight(Long.parseLong(req.getParameter("height")));
      web.setWidth(Long.parseLong(req.getParameter("width")));
      web.setName(req.getParameter("name"));
      web.setNsfw(Long.parseLong(req.getParameter("nsfw")));
      web.setSize(Long.parseLong(req.getParameter("size")));
      web.setPath(req.getParameter("path"));
      web.setThumbnail(req.getParameter("thumbnail"));
      web.setTnHeight(Long.parseLong(req.getParameter("tn_height")));
      web.setTnWidth(Long.parseLong(req.getParameter("tn_width")));
      return web;
   }

}