package omc.common.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="mainService")
	private MainService mainService;
	
	 @RequestMapping(value="/main.omc", method=RequestMethod.GET) 
	 public ModelAndView selectGoodsList(Map<String, Object> commandMap) throws Exception{
		 ModelAndView mv = new ModelAndView("main_layout");
		 
		 List<Map<String, Object>> list = mainService.selectGoodsList(commandMap);
		 mv.addObject("mainList", list);
	
		 return mv;
	 }
	 
	 @RequestMapping(value="/adminMain.omc")
	 public String adminMain(Model model) {
	 	 return "admin_layout";
	 }
}