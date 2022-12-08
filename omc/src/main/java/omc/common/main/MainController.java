package omc.common.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@Resource(name="mainService")
	private MainService mainService;
	
	 @RequestMapping(value="/main.omc", method=RequestMethod.GET) 
	 public ModelAndView selectGoodsList(Map<String, Object> commandMap) throws Exception{
		 ModelAndView mv = new ModelAndView("main_layout");
		 
		 List<Map<String, Object>> list = mainService.selectGoodsList(commandMap);
		 mv.addObject("mainList", list);
	
		 return mv;
	 }
}