package omc.admin.order;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.common.CommandMap;
import omc.common.order.OrderService;
import omc.util.Paging;

@Controller
public class AdminOrderController {

	@Resource(name="adminOrderService")
	private AdminOrderService adminOrderService;
	
	@Resource(name="orderService")
	private OrderService orderService;

	/* 주문 리스트 */
	@RequestMapping(value = "/adminOrderList.omc")
	public ModelAndView adminOrderList(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/order/adminOrderList");
		/* 페이징을 위한 변수 */
		int pageSize = 10; // 페이지당 출력할 회원의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countOrderAll; // 전체 주문 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminOrderList.omc";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}
		
		/* 검색 조건일 경우 사용 변수 */
		String condition = null; // 검색 종류 : 이메일('EMAIL'), 이름('NAME'), 회원등급('RANK')
		String keyword = null; // 검색어
		
		condition = request.getParameter("condition");
		keyword = request.getParameter("keyword");
		if(keyword!=null && keyword.equals("")) {
			keyword = null;
		}
		
		/* 페이징을 위한 값 계산 */
		if(keyword == null || keyword.trim() =="") { // 검색 조건이 아닐 때
			countOrderAll = adminOrderService.orderCount();
		}else { // 검색어를 입력했을 때
			countOrderAll = adminOrderService.orderSearchCount(condition, keyword);
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소 입력
		Paging paging = new Paging(countOrderAll,	pageBlock,
				pageSize ,currentPage, url, searchUrl);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> orderBeanList = new ArrayList<Map<String, Object>>();
		
		/* 페이징 리스트를 가져옴 */
		if(keyword == null || keyword.trim() =="") {
			list = adminOrderService.orderListPaging(START, END);
		}else {
			list = adminOrderService.orderListSearchPaging(condition, keyword, START, END);
		}
		
		for(Map<String, Object> mapObject : list) {
			orderBeanList.add(mapObject);
		}
		
		mv.addObject("orderBeanList", orderBeanList);
		
		/* 페이징을 위한 값 삽입 */
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);
		
		/* SELECT 태그에서 검색 조건 유지를 위한 값 */
		mv.addObject("condition", condition);
		
		return mv;
	}
	
	/* 주문 상세 보기 */
	@RequestMapping("/adminOrderDetail.omc")
	public ModelAndView adminOrderDetail (CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/order/adminOrderDetail");
		Map<String, Object> orderDetail = adminOrderService.selectOrderOId(commandMap.getMap());
		
		mv.addObject("orderDetail", orderDetail);
		
		return mv;
	}
	
	/* 주문 수정 */
	@RequestMapping("/adminOrderModify.omc")
	public ModelAndView adminOrderModify (CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/admin/order/adminOrderModify");
		
		adminOrderService.updateOrderDirect(commandMap.getMap());
		
		mv.addObject("msg", "주문 정보가 수정되었습니다.");
		mv.addObject("url", "/adminOrderList.omc");
		
		return mv;
	}	
	
	/* 매출 리스트 */
	@RequestMapping(value="/adminSellList.omc")
	public ModelAndView adminSellList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/goods/adminSellList");
		
		Map<String,Object> resultMap = adminOrderService.selectGoodsListPaging(commandMap.getMap());
		Map<String,Object> sum = adminOrderService.sellSum(commandMap.getMap());
		String sort = (String)commandMap.get("sort");		
		
		mv.addObject("sum", sum.get("SUM"));
		mv.addObject("sort", sort);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("sellList", resultMap.get("result"));
		
		return mv;
	}
}