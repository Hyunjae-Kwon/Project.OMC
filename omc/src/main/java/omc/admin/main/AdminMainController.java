package omc.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminMainController {
	
	@RequestMapping(value="/adminMainForm.omc")
	public ModelAndView adminMainForm(Model model) {
		ModelAndView mv = new ModelAndView("admin/admin/adminMainForm");
		return mv;
	}
}