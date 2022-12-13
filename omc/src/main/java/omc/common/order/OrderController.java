package omc.common.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.cart.CartService;
import omc.common.common.CommandMap;
import omc.common.goods.GoodsService;
import omc.member.login.LoginService;

@Controller
public class OrderController {
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="cartService")
	private CartService cartService;

	@RequestMapping(value="/orderForm.omc", method = RequestMethod.GET)
	public ModelAndView orderForm (HttpServletRequest request, CommandMap commandMap) throws Exception {
		/* PID를 넘겨받아 상품 정보를 읽어옴 */
		ModelAndView mv = new ModelAndView("order/orderForm");
		
		String orderCount = (String)commandMap.get("orderCount");
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		Map<String,Object> memInfo = loginService.selectMember(loginId);
        Map<String,Object> goodsInfo = goodsService.goodsDetail(commandMap.getMap());
        
		mv.addObject("orderCount", orderCount);
		mv.addObject("goodsInfo",goodsInfo);
		mv.addObject("memInfo", memInfo);
		
		return mv;
	}
	
	@RequestMapping(value = "/order.omc")
	public ModelAndView order(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("order/order");
	
		int OID = orderService.selectOIDMax();
		model.addAttribute("msg", "주문을 완료했습니다.");
		String urlParam = "/orderResult.omc?OID=" + OID;
		model.addAttribute("url", urlParam);
		
		return mv;

	}
	
	@RequestMapping(value = "/orderResult.omc")
	public ModelAndView orderResult(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderResult");
		
		List<Map<String, Object>> orderInfo = orderService.selectOrderOId(commandMap.getMap());	
		Map<String,Object> memInfo = loginService.selectMemberId(commandMap.getMap());
		
		mv.addObject("orderResult", orderInfo);
		mv.addObject("memInfo", memInfo);
		
		System.out.println(orderInfo);
		System.out.println(memInfo);
		
		return mv;

	}

	
	
	@RequestMapping(value = "/cartOrderForm.omc")
	@ResponseBody
	public ModelAndView cartOrderForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("cartOrderForm");
		List<Map<String, Object>> cartInfo = cartService.selectCartGID(commandMap.getMap());
		Map<String,Object> memInfo = loginService.selectMemberId(commandMap.getMap());
		mv.addObject("cartInfo", cartInfo);
		mv.addObject("memInfo", memInfo);
		return mv;
	}
	
	@RequestMapping(value = "/cartOrder.omc")
	public ModelAndView cartOrder(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("orderResult");
		List<Map<String, Object>> cartPay = orderService.insertOrderCart(commandMap.getMap());		
		mv.addObject("cartPay", cartPay);	
		return mv;

	}


}