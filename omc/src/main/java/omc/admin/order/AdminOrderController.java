package omc.admin.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;


@Controller
public class AdminOrderController {

	@Resource(name="adminOrderService")
	private AdminOrderService adminOrderService;
	
	@RequestMapping("/adminOrderList.omc")
	public ModelAndView adminOrderList (HttpServletRequest request, CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/order/adminOrderList");
		//검색 조건
		String condition = null;
		String keyword = null;
		
		condition = request.getParameter("condition");
		keyword = request.getParameter("keyword");
		if(keyword!=null && keyword.equals("")) {
			keyword = null;
		}
		List<Map<String, Object>> orderList = adminOrderService.orderList();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> orderBeanList = new ArrayList<Map<String, Object>>();
		
		if(keyword == null || keyword.trim() == "") {
			list = adminOrderService.orderList();
		} else {
			list = adminOrderService.orderListSearch(condition, keyword);
		}
		for(Map<String, Object> mapObject : list) {
			orderBeanList.add(mapObject);
		}

		mv.addObject("orderList", orderList);
		
		mv.addObject("orderBeanList", orderBeanList);
		
		mv.addObject("condition", condition);
		
		return mv;
	}

	@RequestMapping("/adminOrderDetail.omc")
	public ModelAndView adminOrderDetail (CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/order/adminOrderDetail");
		Map<String, Object> orderDetail = adminOrderService.selectOrderOId(commandMap.getMap());
		
		mv.addObject("orderDetail", orderDetail);
		
		return mv;
	}
	
	@RequestMapping("/adminOrderModify.omc")
	public ModelAndView adminOrderModify (CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/admin/order/adminOrderModify");
		
		adminOrderService.updateOrderDirect(commandMap.getMap());
		
		mv.addObject("msg", "주문 정보가 수정되었습니다.");
		mv.addObject("url", "/adminOrderList.omc");
		
		return mv;
	}
	
}

