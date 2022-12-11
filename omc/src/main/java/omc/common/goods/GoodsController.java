package omc.common.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.common.CommandMap;

@Controller
public class GoodsController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "goodsService")
	GoodsService goodsService;
	
	// 구독 페이지
	@RequestMapping(value = "/goods/sub")
	public ModelAndView subList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("subList");	
		return mv;
	}
	
	@RequestMapping(value ="/goods/goodsSubList")
	public ModelAndView goodsSubList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = goodsService.goodsSubList(commandMap.getMap());
		mv.addObject("list", list);
		if(list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}
		return mv;
	}

	// 일반 페이지
	@RequestMapping(value = "/goods/sale")
	public ModelAndView saleList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("saleList");	
		return mv;

	}
	@RequestMapping(value ="/goods/goodsSaleList")
	public ModelAndView goodsSaleList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = goodsService.goodsSaleList(commandMap.getMap());
		mv.addObject("list", list);
		if(list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}
		return mv;
	}

	// 구독 상세 페이지 여기된다 /
	@RequestMapping(value = "/goods/sub/page")  // , method = RequestMethod.GET
	public ModelAndView subDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goodsSubDetail");
		List<Map<String, Object>> goodsSubDetail = goodsService.getsubDetail(commandMap.getMap());
		
//		옵션 list  
		List<String> list11 = new ArrayList<String>();
		List<String> list12 = new ArrayList<String>();
		List<String> list13 = new ArrayList<String>();
	// if .
		if(goodsSubDetail.get(0).get("GOODS_OP4") != null) {
			if(goodsSubDetail.get(0).get("GOODS_OP3") != null) {
				if(goodsSubDetail.get(0).get("GOODS_OP2") != null) {
					StringTokenizer op4 = new StringTokenizer((String) goodsSubDetail.get(0).get("GOODS_OP4"),  ",");
					StringTokenizer op3 = new StringTokenizer((String) goodsSubDetail.get(0).get("GOODS_OP3"),  ",");
					StringTokenizer op2 = new StringTokenizer((String) goodsSubDetail.get(0).get("GOODS_OP2"),  ",");
				// list 값 담고 
					while(op4.hasMoreElements()) {
						list11.add(op4.nextToken());
					}
					while(op3.hasMoreElements()) {
						list12.add(op3.nextToken());
					}
					while(op2.hasMoreElements()) {
						list13.add(op2.nextToken());
					}
				}
			}
		}
		
		List<Map<String, Object>> Review = goodsService.getReview(commandMap.getMap());
		List<Map<String, Object>> ReviewRe = goodsService.getReviewRe(commandMap.getMap());
		HttpSession session = request.getSession();// 세션 값 불러오고 
		String USER_ID = (String)session.getValue("USER_ID");// 값을 String 저장하고
		mv.addObject("goodsSubDetail", goodsSubDetail);
		mv.addObject("review", Review);
		mv.addObject("reviewRe", ReviewRe);
		mv.addObject("list11", list11);
		mv.addObject("list12", list12);
		mv.addObject("list13", list13);
		session.setAttribute("USER_ID", USER_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
		return mv;
	}

	// 일반 상세 페이지
	@RequestMapping(value = "/goods/sale/page") // , method = RequestMethod.POST
	public ModelAndView saleDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goodsSaleDetail");
		List<Map<String, Object>> goodsSaleDetail = goodsService.getsaleDetail(commandMap.getMap());
//		옵션 list  
		List<String> list11 = new ArrayList<String>();
		List<String> list12 = new ArrayList<String>();
		List<String> list13 = new ArrayList<String>();
	// if .
		if(goodsSaleDetail.get(0).get("GOODS_OP4") != null) {
			if(goodsSaleDetail.get(0).get("GOODS_OP3") != null) {
				if(goodsSaleDetail.get(0).get("GOODS_OP2") != null) {
					StringTokenizer op4 = new StringTokenizer((String) goodsSaleDetail.get(0).get("GOODS_OP4"),  ",");
					StringTokenizer op3 = new StringTokenizer((String) goodsSaleDetail.get(0).get("GOODS_OP3"),  ",");
					StringTokenizer op2 = new StringTokenizer((String) goodsSaleDetail.get(0).get("GOODS_OP2"),  ",");
				// list 값 담고 
					while(op4.hasMoreElements()) {
						list11.add(op4.nextToken());
					}
					while(op3.hasMoreElements()) {
						list12.add(op3.nextToken());
					}
					while(op2.hasMoreElements()) {
						list13.add(op2.nextToken());
					}
				}
			}
		}
		
		List<Map<String, Object>> Review2 = goodsService.getReview2(commandMap.getMap());
		List<Map<String, Object>> ReviewRe = goodsService.getReviewRe(commandMap.getMap());
		HttpSession session = request.getSession();// 세션 값 불러오고 
		String USER_ID = (String)session.getValue("USER_ID");// 값을 String 저장하고
		mv.addObject("goodsSaleDetail", goodsSaleDetail);
		mv.addObject("review2", Review2);
		mv.addObject("reviewRe", ReviewRe);
		mv.addObject("list11", list11);
		mv.addObject("list12", list12);
		mv.addObject("list13", list13);
		session.setAttribute("USER_ID", USER_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
		return mv;
	}
	
	@RequestMapping(value = "/insertgoodsimgPage")
	public ModelAndView insertgoodsimg(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("insertgoodsimgPage");
		return mv;

	}
	@RequestMapping(value = "/insertgoodsthumbPage")
	public ModelAndView insertgoodsthumb(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("insertgoodsthumbPage");
		return mv;
	}
	
	/* 전체 상품 리스트 */
	@RequestMapping(value="/allGoodsList.omc")
	public ModelAndView allGoodsList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/goods/allGoodsList");
		
		Map<String,Object> resultMap = goodsService.allGoodsList(commandMap.getMap());
		
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("allGoodsList", resultMap.get("result"));
		
		return mv;
	}
	
	/* 신상품 리스트 */
	@RequestMapping(value="/allGoodsListNew.omc", method=RequestMethod.GET)
	public ModelAndView newGoodsList(Map<String, Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("goods/newGoodsList");
		
		List<Map<String, Object>> list = goodsService.newGoodsList(commandMap);
		 mv.addObject("newGoodsList", list);
	
		 return mv;
	}
	
	/* 베스트 상품 리스트 */
	@RequestMapping(value="/allGoodsListBest.omc", method=RequestMethod.GET)
	public ModelAndView bestGoodsList(Map<String, Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("goods/bestGoodsList");
		
		List<Map<String, Object>> list = goodsService.bestGoodsList(commandMap);
		System.out.println(list);
		 mv.addObject("bestGoodsList", list);
	
		 return mv;
	}
	
	/* 상품 추가 폼 */
	@RequestMapping(value = "/goodsWriteForm.omc")
	public ModelAndView goodsWriteForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsWriteForm");
	
		return mv;
	}
	
	/* 상품 추가 기능 */
	@RequestMapping(value = "/goodsWrite.omc")
	public ModelAndView goodsWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView("redirect:/main.omc");
		
		goodsService.goodsWrite(commandMap.getMap(), request);
		
		return mv;
	}
	
//	/* 상품 추가 완료 */
//	@RequestMapping(value = "/goodsWrite.omc")
//	@ResponseBody
//	public ModelAndView goodsWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {		
//		Map<String, Object> goodsWrite = goodsService.goodsWriteForm(commandMap.getMap());				
//		ModelAndView mv = new ModelAndView("goods/goodsWrite");
//		mv.addObject("goodsWrite", goodsWrite);
//		mv.addObject("goodsWrite", request);
//		
//		return mv;
//	}
	
//	subList()구독판매페이지
//	saleList()일반판매페이지
//	subDetail()구독판매상세페이지
//	saleDetail()일반판매상세페이지
	// goodsListDetail 리스트 상세

}
