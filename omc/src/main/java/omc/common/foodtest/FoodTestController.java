package omc.common.foodtest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.common.main.MainService;

@Controller
public class FoodTestController {
	
	@Resource(name="foodTestService")
	private FoodTestService foodTestService;
	
	@RequestMapping(value="/foodTestForm.omc") 
	public ModelAndView foodTestForm(Map<String, Object> commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("foodtest/foodTestForm");
		
		return mv;
	}
	
	@RequestMapping(value="/foodTestResult.omc")
	@ResponseBody
	public ModelAndView foodTestResult(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("foodtest/foodTestResult");
		
//		String category = (String) commandMap.get("GD_CATEGORY");
		System.out.println(request);
		String keyword = request.getParameter("GD_CATEGORY");
		
		Map<String, Object> result = foodTestService.testResultSet(keyword);
		List<Map<String, Object>> list = foodTestService.testResultGoods(keyword);
		mv.addObject("testResult", result);
//		mv.addObject("GD_CATEGORY", category);
		mv.addObject("resultGoods", list);
		 
		return mv;
	}
}