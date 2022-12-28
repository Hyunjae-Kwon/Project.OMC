package omc.member.mypage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.board.BoardService;
import omc.common.common.CommandMap;
import omc.common.goods.GoodsService;
import omc.common.order.OrderService;
import omc.member.login.LoginService;

@Controller
public class MyPageController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "loginService")
	private LoginService loginService;
	
	@Resource(name = "myPageService")
	private MyPageService myPageService;
	
	@Resource(name = "orderService")
	private OrderService orderService;
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	
	/* 마이페이지 */
	@RequestMapping(value = "/myPage.omc")
	public ModelAndView selectMemberId(CommandMap commandMap, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("member/myPage");
		
		//로그인 시 마이페이지로 넘어갈 수 있는 코드
		HttpSession session = request.getSession();
		String MEM_ID = (String)session.getValue("MEM_ID");
		session.setAttribute("MEM_ID", MEM_ID);
		if(request.getSession().getAttribute("MEM_ID")==null){
			 response.sendRedirect("/loginForm.omc"); 
		}
		
		return mv;
	}	
	
	/* 정보수정 폼 */
	@RequestMapping(value="/myInfoModify.omc", method = RequestMethod.GET)
	public ModelAndView updateMember(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("member/myInfoModifyForm");
		
		//현재 아이디의 회원 정보를 수정하는 코드
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");
		commandMap.put("MEM_ID", id);
		
		Map<String, Object> map = myPageService.selectMemberId(commandMap.getMap());
		mv.addObject("map", map);
		
		return mv;
	}
	
	/* 정보수정 완료 */
	@RequestMapping(value = "/myInfoModifyOk.omc", method = RequestMethod.POST)
	public ModelAndView myInfoModifyOk(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/myPage.omc");
		/* System.out.println(commandMap.get("MEM_ID")); */ 
		
		myPageService.updateMember(commandMap.getMap(), request);
		
		return mv;
	}
	
	/* 회원탈퇴 */
	@RequestMapping(value="/myInfoDelete.omc", method = RequestMethod.POST)
	public ModelAndView deleteMember(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/logout.omc");
			
		HttpSession session = request.getSession();
		commandMap.put("MEM_ID",session.getAttribute("MEM_ID"));
		myPageService.deleteMember(commandMap.getMap());
		
		return mv;
	}
	
	/* 마이페이지 주문 내역 */
	@RequestMapping(value="/myOrderList.omc")
	public ModelAndView myOrderList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("member/myInfoOrder");
			
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");
		commandMap.put("MEM_ID", id);
				
		Map<String, Object> orderList = orderService.myOrderListPaging(commandMap.getMap());
		mv.addObject("paginationInfo", (PaginationInfo)orderList.get("paginationInfo"));
		mv.addObject("orderCount", orderList.size());
		mv.addObject("order", orderList.get("result"));
		
		return mv;
	}
	
	/* 마이페이지 주문 상세 */
	@RequestMapping(value ="/myOrderDetail.omc", method = RequestMethod.GET)
	public ModelAndView myOrderDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("member/myInfoOrderDetail");
	       
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
			             
		List<Map<String, Object>> orderInfo = orderService.selectOrderODNum(commandMap.getMap());
		Map<String, Object> payInfo = orderService.selectPayODNum(commandMap.getMap());
		Map<String,Object> memInfo = loginService.selectMember(loginId);
	       
		mv.addObject("orderResult", orderInfo);
		mv.addObject("payResult", payInfo);
		mv.addObject("memInfo", memInfo);
	          
		return mv;
	}
	
	/* 마이페이지 주문 취소 */
	@RequestMapping(value = "/deleteOrder.omc", method = RequestMethod.POST)
	public ModelAndView deleteOrder(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("member/myInfoOrderDelete");
		
		// ordercommand 테이블 삭제
		orderService.deleteOrder(commandMap.getMap());
		// goods 재고 증가
		goodsService.sellCountUpdateC(commandMap.getMap());
		// goods 판매량 감소
		goodsService.saleCountUpdateC(commandMap.getMap());
		// payment 테이블 삭제
		orderService.deletePay(commandMap.getMap());
	
		model.addAttribute("msg", "주문을 취소했습니다.");
		String urlParam = "/myOrderList.omc";
		model.addAttribute("url", urlParam);
		
		return mv;

	}
	
	/* 사용자가 작성한 후기리스트 */
	@RequestMapping(value="/myReviewList.omc", method = RequestMethod.GET)
	public ModelAndView myReviewList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("member/myInfoReview");
			
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");
		commandMap.put("MEM_ID", id);
		
		List<Map<String, Object>> map = boardService.myReviewList(commandMap.getMap());
		mv.addObject("reviewCount", map.size());
		mv.addObject("review", map);
		
		return mv;
	}
	
	/* 사용자가 작성한 Qna */
	@RequestMapping(value="/myQnaList.omc", method = RequestMethod.GET)
	public ModelAndView myQnaList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("member/myInfoQna");
			
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");
		commandMap.put("MEM_ID", id);
		
		List<Map<String, Object>> map = boardService.myQnaList(commandMap.getMap());
		mv.addObject("qnaCount", map.size());
		mv.addObject("qna", map);
		
		return mv;
	}	
}