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
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

	/* 주문하기 폼 (상품 상세 -> 바로 주문) */
	@RequestMapping(value="/orderForm.omc", method = RequestMethod.GET)
	public ModelAndView orderForm (HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderForm");
		
		String orderCount = (String)commandMap.get("orderCount");
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		Map<String,Object> memInfo = loginService.selectMember(loginId);
        Map<String,Object> goodsInfo = goodsService.goodsDetail(commandMap.getMap());
        /* OD_NUM 최대값 구해서 넘겨주기 */
		int orderNum = orderService.selectODNumMax() + 1;
        
		mv.addObject("orderNum", orderNum);
		mv.addObject("orderCount", orderCount);
		mv.addObject("goodsInfo",goodsInfo);
		mv.addObject("memInfo", memInfo);
		
		return mv;
	}
	
	/* 주문하기 폼 (장바구니 -> 선택 상품 주문) */
	@RequestMapping(value="/orderCartForm.omc", method = RequestMethod.GET)
	public ModelAndView orderCartForm (HttpServletRequest request, CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderCartForm");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		/* 회원 정보를 이용해서 주문 정보 입력 */
		Map<String,Object> memInfo = loginService.selectMember(loginId);
		/* 카트에서 업데이트된 CT_NUM을 이용해서 상품 정보 호출 */
		List<Map<String, Object>> cartInfo = cartService.cartDetail(loginId);
		/* OD_NUM 최대값 구해서 넘겨주기 */
		int orderNum = orderService.selectODNumMax() + 1;
		
		mv.addObject("orderNum", orderNum);
		mv.addObject("cartInfo",cartInfo);
		mv.addObject("memInfo", memInfo);
		
		return mv;
	}
	
	/* 주문하기 기능 (상품 상세 -> 바로 주문) */
	@RequestMapping(value = "/order.omc", method = RequestMethod.POST)
	public ModelAndView order(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("order/order");
		
		// DB에 주문 정보 입력
		orderService.insertOrderDirect(commandMap.getMap(), request);
		
		goodsService.sellCountUpdate(commandMap.getMap());
		goodsService.saleCountUpdate(commandMap.getMap());
		orderService.insertPay(commandMap.getMap());
	
		int OID = orderService.selectOIDMax();
		model.addAttribute("msg", "주문을 완료했습니다.");
		String urlParam = "/orderResult.omc?OD_OID=" + OID;
		model.addAttribute("url", urlParam);
		
		return mv;

	}
	
	/* 주문하기 기능 (장바구니 -> 선택 상품 주문) */
	@RequestMapping(value = "/orderCart.omc", method = RequestMethod.POST)
	public ModelAndView orderCart(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("order/order");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		// DB에 주문 정보 입력
		orderService.insertOrderCart(commandMap.getMap());
		cartService.delCartOrder(loginId);
		goodsService.sellCountUpdate(commandMap.getMap());
		goodsService.saleCountUpdate(commandMap.getMap());
		orderService.insertPay(commandMap.getMap());
	
		model.addAttribute("msg", "주문을 완료했습니다.");
		String urlParam = "/orderCartResult.omc";
		model.addAttribute("url", urlParam);
		
		return mv;
	}
	
	/* 주문완료 (상품 상세 -> 바로 주문) */
	@RequestMapping(value = "/orderResult.omc")
	public ModelAndView orderResult(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderResult");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		
		Map<String, Object> orderInfo = orderService.selectOrderOID(commandMap.getMap(), request);	
		Map<String,Object> memInfo = loginService.selectMember(loginId);
		
		mv.addObject("orderResult", orderInfo);
		mv.addObject("memInfo", memInfo);
		
		System.out.println(orderInfo);
		System.out.println(memInfo);
		
		return mv;
	}
    
    /* 주문완료 (장바구니 -> 선택 상품 주문) */
    @RequestMapping(value = "/orderCartResult.omc", method = RequestMethod.GET)
    public ModelAndView orderCartResult(CommandMap commandMap, HttpServletRequest request) throws Exception {
       ModelAndView mv = new ModelAndView("order/orderCartResult");
       
       String loginId = (String) request.getSession().getAttribute("MEM_ID");
       int orderNum = orderService.selectODNumMax();
             
       List<Map<String, Object>> orderInfo = orderService.selectOrderODNum(orderNum);
       Map<String, Object> payInfo = orderService.selectPayODNum(orderNum); //페이정보 불러오기 추가
       Map<String,Object> memInfo = loginService.selectMember(loginId);
       
       mv.addObject("orderResult", orderInfo);
       mv.addObject("payResult", payInfo); //페이정보 payResult넣기 추가
       mv.addObject("memInfo", memInfo);
          
       return mv;
    }
}