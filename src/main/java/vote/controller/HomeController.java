package vote.controller;

import java.text.DateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/test"})
public class HomeController
{
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
  
   @RequestMapping(value={"/hello.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
   public String home(Model model)
   {
     Date date = new Date();
     DateFormat dateFormat = DateFormat.getDateTimeInstance(1, 1);
     String formattedDate = dateFormat.format(date);
     
     model.addAttribute("serverTime", formattedDate);
     return "home";
   }
 }
