package omc.admin.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.util.Paging;

@Controller
public class AdminReviewController {
	
	@Resource(name="adminCommunityService")
	private AdminCommunityService adminCommunityService;

	/* 고객 후기 리스트 */
	@RequestMapping(value="/adminReviewList.omc")
	public ModelAndView adminReviewList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminReviewListAjax");
		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int reviewListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "noticeList.omc";
		String searchUrl = "";
		
		List<Map<String, Object>> list = adminCommunityService.reviewListPaging(START, END);
		List<Map<String, Object>> reviewListArr = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> reviewList = adminCommunityService.reviewList();
		for(Map<String, Object> mapObject : list) {
			reviewListArr.add(mapObject);
		}
		reviewListCount = adminCommunityService.reviewListCount();
		
		Paging paging = new Paging(reviewListCount,	pageBlock,
		pageSize ,currentPage, url, searchUrl);
		
		/* 페이징을 위한 값 삽입 */
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);

		mv.addObject("reviewListArr", reviewListArr);
		mv.addObject("reviewList", reviewList);
		
		return mv;
	}
	
	/* 고객 후기 삭제 */
	@RequestMapping(value="/adminReviewDeleteAjax.omc")
	public ModelAndView adminReviewDeleteAjax(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminReviewListAjax");
		
		adminCommunityService.deleteCommunityId(commandMap.getMap());
	
		return mv;
	}	
}