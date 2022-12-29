package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.common.goods.GoodsService;
import omc.member.login.LoginService;

@Controller
public class CartController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "cartService")
	CartService cartService;
	
	@Resource(name = "goodsService")
	GoodsService goodsService;
	
	@Resource(name = "loginService")
	LoginService loginService;


	/* 내 장바구니 리스트 */
	@RequestMapping(value="/myCart.omc", method = RequestMethod.GET)
	public ModelAndView myCart (CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("cart/cartList");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		List<Map<String, Object>> list = cartService.selectCartList(loginId);
        
        mv.addObject("cartList", list);
		
		return mv;
	}
	
	/* 장바구니 수량 변경 */
	@RequestMapping(value="/updateMyCart.omc", method=RequestMethod.POST)
	public ModelAndView updateMyCart(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView("jsonView");
		
		cartService.updateMyCart(commandMap.getMap());
		
		return mv;
	}
	
	/* 장바구니 상품 삭제 */
	@RequestMapping(value = "/delSelectMyCart.omc")
	public ModelAndView delSelectMyCart(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("cart/cartList");
		
		cartService.delSelectMyCart(commandMap.getMap());
	 
		return mv;
	}
	
	/* 상품 장바구니에 추가 */
	@RequestMapping(value="insertCart.omc", method=RequestMethod.POST)
	@ResponseBody
    public boolean insertCart(CommandMap commandMap,HttpServletRequest request) throws Exception{
    	
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		commandMap.put("MEM_ID", loginId);
		
    	int i = cartService.insertCart(commandMap.getMap());
    	if(i == 0) {
    		return false;
    	}
    	return true;
	}
	
	/* 장바구니 구매 상품 카트 번호 업데이트 */
	@RequestMapping(value="/updateNum.omc", method=RequestMethod.POST)
	public ModelAndView updateNum(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		
		cartService.updateNum(commandMap.getMap());
		
		return mv;
	}
}