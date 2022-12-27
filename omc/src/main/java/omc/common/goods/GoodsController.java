package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.board.BoardService;
import omc.common.common.CommandMap;
import omc.member.login.LoginService;

@Controller
public class GoodsController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "goodsService")
	GoodsService goodsService;
	
	@Resource(name = "loginService")
	LoginService loginService;
	
	@Resource(name = "boardService")
	BoardService boardService;
	
	/* 전체 상품 리스트 */
	@RequestMapping(value="/allGoodsList.omc")
	public ModelAndView allGoodsList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("goods/allGoodsList");
		
		Map<String,Object> resultMap = goodsService.allGoodsList(commandMap.getMap());
		String sort = (String)commandMap.get("sort");
		System.out.println(sort);
		
		mv.addObject("sort", sort);
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
	
	/* 카테고리 상품 리스트 */
	@RequestMapping(value="/allGoodsListCategory.omc")
	public ModelAndView categoryGoodsList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("goods/categoryGoodsList");
		
		String GD_CATEGORY = (String)commandMap.get("GD_CATEGORY");
		Map<String,Object> resultMap = goodsService.categoryGoodsListPaging(commandMap.getMap());
	
		List<Map<String, Object>> list = goodsService.categoryGoodsList(commandMap.getMap());
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("category", GD_CATEGORY);
		mv.addObject("categoryGoodsList", list);
		
		return mv;
	}
	
	/* 상품 상세 페이지 */
	@RequestMapping(value = "/goodsDetail.omc") // , method = RequestMethod.POST
	public ModelAndView goodsDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsDetail");
		Map<String, Object> goodsDetail = goodsService.goodsDetail(commandMap.getMap());
		List<Map<String, Object>> goodsReview = goodsService.goodsReview(commandMap.getMap());
		List<Map<String, Object>> goodsQna = goodsService.goodsQna(commandMap.getMap());
		
		HttpSession session = request.getSession();// 세션 값 불러오고 
		String MEM_ID = (String)session.getValue("MEM_ID");// 값을 String 저장하고
		mv.addObject("goods", goodsDetail);
		mv.addObject("reviewList", goodsReview);
		mv.addObject("qnaList", goodsQna);
		session.setAttribute("MEM_ID", MEM_ID);// 세션정보를 MEM_ID 에 담아 jsp로 리턴
		
		return mv;
	}
	
	/* 상품 검색 */
	@RequestMapping(value="/searchGoodsList.omc", method=RequestMethod.GET)
	public ModelAndView searchGoodsList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("goods/searchGoodsList");
				
		String keyword = request.getParameter("keyword");
		
		List<Map<String, Object>> list = goodsService.searchGoodsList(commandMap.getMap(), request);
		
		mv.addObject("keyword", keyword);
		mv.addObject("goods", list);
		
		return mv;
	}
	
	/* 상품 후기 작성 */
	@RequestMapping("/goodsReviewForm.omc")
	public ModelAndView goodsReviewForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsReviewForm");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
        
		Map<String,Object> memInfo = loginService.selectMember(loginId);		
		Map<String, Object> goods = goodsService.goodsDetail(commandMap.getMap());
		
		mv.addObject("memInfo", memInfo);
		mv.addObject("goods", goods);

		return mv;
	}
	
	/* 상품 후기 작성 */
	@RequestMapping("/goodsReview.omc")
	public ModelAndView goodsReview(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsReview");
		
		boardService.insertReview(commandMap.getMap());

		mv.addObject("msg", "후기를 등록하였습니다.");
		String urlParam = "/goodsDetail.omc?GD_GID=" + request.getParameter("BD_GID");
		mv.addObject("url", urlParam);

		return mv;
	}
	
	/* 상품 문의 작성 폼 */
	@RequestMapping("/goodsQnaForm.omc")
	public ModelAndView goodsQnaForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsQnaForm");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
        
		Map<String,Object> memInfo = loginService.selectMember(loginId);
		Map<String, Object> goods = goodsService.goodsDetail(commandMap.getMap());
		
		mv.addObject("memInfo", memInfo);
		mv.addObject("goods", goods);

		return mv;
	}
	
	/* 상품 문의 작성 */
	@RequestMapping("/goodsQna.omc")
	public ModelAndView goodsQna(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsQna");
		
		boardService.insertQna(commandMap.getMap());

		mv.addObject("msg", "상품 문의를 등록하였습니다.");
		String urlParam = "/goodsDetail.omc?GD_GID=" + request.getParameter("BD_GID");
		mv.addObject("url", urlParam);

		return mv;
	}
}
