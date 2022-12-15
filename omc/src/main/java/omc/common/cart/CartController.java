package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.common.goods.GoodsService;
import omc.member.login.LoginService;

@Controller
public class CartController {
	@Resource(name = "cartService")
	CartService cartService;
	
	@Resource(name = "goodsService")
	GoodsService goodsService;
	
	@Resource(name = "loginService")
	LoginService loginService;

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
	
	/* 상품 장바구니에 저장 */
//	@RequestMapping(value="/insertCart.omc", method = RequestMethod.GET)
//	public ModelAndView insertCart (HttpServletRequest request, CommandMap commandMap) throws Exception {
//		ModelAndView mv = new ModelAndView("cart/cartList");
//		
//		String orderCount = (String)commandMap.get("orderCount");
//		String goodsId = (String)commandMap.get("GD_GID");
//		String loginId = (String) request.getSession().getAttribute("MEM_ID");
//		
//		Map<String,Object> memInfo = loginService.selectMember(loginId);
////        Map<String,Object> goodsInfo = goodsService.selectGoods(goodsId);
//        
//		mv.addObject("orderCount", orderCount);
////		mv.addObject("goodsInfo",goodsInfo);
//		mv.addObject("memInfo", memInfo);
//		
//		// DB에 장바구니 정보 입력
//		cartService.insertCart(commandMap.getMap(), request);
//		
//		return mv;
//	}
	
	@RequestMapping(value="insertCart.omc", method=RequestMethod.POST)
	@ResponseBody
    public boolean addCart(CommandMap commandMap,HttpServletRequest request) throws Exception{
    	
    	String loginId="";
    	
    	if(request.getSession().getAttribute("MEM_ID")!=null) {
    		loginId = (String)request.getSession().getAttribute("MEM_ID");
    	}
		
    	if(loginId.isEmpty()) {
    		
    	} else {
    		commandMap.put("MEM_ID", loginId);
    	}

    	
    	int i = cartService.insertCart(commandMap.getMap());
    	if(i == 0) {
    		return false;
    	}
    	return true;
	}
	
	/* 내 장바구니 리스트 */
	@RequestMapping(value="/myCart.omc", method = RequestMethod.GET)
	public ModelAndView myCart (CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("cart/cartList");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		List<Map<String, Object>> list = cartService.selectCartList(loginId);
        Map<String,Object> memInfo = loginService.selectMember(loginId);
        
        mv.addObject("cartList", list);
		mv.addObject("memInfo", memInfo);
		
		return mv;
	}

}
