package com.grigorovartem.servletstudy.app;

import com.grigorovartem.servletstudy.dao.WebmDao;
import com.grigorovartem.servletstudy.validators.WebmValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebmController
{
   @Autowired
   WebmValidator webmValidator;

   @Autowired
   WebmDao webmDao;

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String getWebm()
   {
      return "getWebm";
   }

   @RequestMapping(value = "/hellokitty", method = RequestMethod.GET)
   public String helloKitty()
   {
      return "helloKitty";
   }
}
