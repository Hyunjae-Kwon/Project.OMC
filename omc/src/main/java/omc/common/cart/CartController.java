package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;

@Controller
public class CartController {
	@Resource(name = "cartService")
	CartService cartService;

	@RequestMapping(value = "/cartList.omc")
	@ResponseBody
	public ModelAndView cartList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/cartList");// jsonView 동작안함
		List<Map<String, Object>> cartList = cartService.cartList(commandMap.getMap());
		HttpSession session = request.getSession();// 세션 값 불러오고
		String MEM_ID = (String) session.getValue("MEM_ID");// 값을 String 저장하고
		mv.addObject("cartList", cartList);
		session.setAttribute("MEM_ID", MEM_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
		
		return mv;

	}

	
	
	@RequestMapping(value = "/cart/cartPut.omc")
	@ResponseBody
	public ModelAndView cartPut(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/cartDetail");// .jsp
		HttpSession session = request.getSession();// 세션 값 불러오고
		String MEM_ID = (String) session.getValue("MEM_ID");// 값을 String 저장하고
		cartService.cartPut(commandMap.getMap()); // 이게 맵으로 받아온 값을 cartPut으로 넣는다는
		session.setAttribute("MEM_ID", MEM_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
		
		return mv; // 리턴
	}

	@RequestMapping(value = "/cart/cartDelete.omc")
	@ResponseBody
	public ModelAndView cartDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/cart");// .jsp
		cartService.cartDelete(commandMap.getMap()); // 이게 맵으로 받아온 값을 cartPut으로 넣는다는
	 
		return mv; // 리턴
	}

}
