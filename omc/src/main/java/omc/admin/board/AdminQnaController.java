package omc.admin.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.util.Paging;

@Controller
public class AdminQnaController {
	
	@Resource(name="adminCommunityService")
	private AdminCommunityService adminComService;
	
	@RequestMapping(value="/adminQnaList.omc", method = RequestMethod.GET)
	public ModelAndView adminQnaList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/community/adminQnaListAjax");
		/* 페이징을 위한 변수 */
		int pageSize = 10; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int qnaListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminQnaList.omc";
		String searchUrl = "";
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}
		
		List<Map<String, Object>> list = adminComService.qnaListPaging(START, END);
		List<Map<String, Object>> qnaListArr = new ArrayList<Map<String, Object>>();
		
		for(Map<String, Object> mapObject : list) {
			qnaListArr.add(mapObject);
		}
		
		qnaListCount = adminComService.qnaListCount();
		
		Paging paging = new Paging(qnaListCount, pageBlock,
		pageSize ,currentPage, url, searchUrl);
		
		/* 페이징을 위한 값 삽입 */
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);

		mv.addObject("qnaListArr", qnaListArr);
		
		return mv;
	}
	
	@RequestMapping(value="/adminQnaDelete.omc")
	public ModelAndView adminQnaDelete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminQnaDelete");
		
		mv.addObject("msg", "게시글 삭제가 완료되었습니다.");
		mv.addObject("url", "/adminQnaList.omc");
		adminComService.deleteCommunityId(commandMap.getMap());
		
		return mv;
	}
	
	
	@RequestMapping(value="/adminQnaDeleteAjax.omc")
	public ModelAndView adminQnaDeleteAjax(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminQnaList");
				
	
		adminComService.deleteCommunityId(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/adminQnaDetail.omc", method = RequestMethod.GET)
	public ModelAndView adminQnaDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("admin/community/adminQnaDetailAjax");
		
		Map<String, Object> qnaMap = adminComService.selectQnaId(commandMap.getMap());
		commandMap.put("BC_NUM",commandMap.get("BD_NUM"));
		List<Map<String, Object>> commentList = adminComService.commentListId(commandMap.getMap());
	
		List<Map<String, Object>> comList = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> mapObject : commentList) {
			comList.add(mapObject);
		}

		mv.addObject("qnaMap", qnaMap);	
		mv.addObject("comList", comList);	
		mv.addObject("commentList", commentList);			
		
		return mv;
	}

	@RequestMapping(value="/adminQnaComWrite.omc")
	public ModelAndView adminQnaComWrite(CommandMap commandMap) throws Exception {	
		ModelAndView mv = new ModelAndView("/admin/community/adminQnaComWrite");
				
		mv.addObject("msg", "댓글 작성이 완료되었습니다.");
		mv.addObject("url", "/adminQnaDetailAjax.omc?BD_NUM="+commandMap.get("BC_NUM"));
		adminComService.insertComment(commandMap.getMap());		
		
		return mv;
	}
	
	@RequestMapping(value="/adminQnaComWriteAjax.omc")
	@ResponseBody
	public String adminQnaComWriteAjax(CommandMap commandMap) throws Exception {	
		
		adminComService.insertComment(commandMap.getMap());		
		
		return "good";
	}

	@RequestMapping(value="/adminQnaComDelete.omc")
	public ModelAndView adminQnaComDelete(CommandMap commandMap) throws Exception {

		ModelAndView mv = new ModelAndView("/admin/community/adminQnaComDelete");
		
		mv.addObject("msg", "댓글 삭제가 완료되었습니다.");
		mv.addObject("url", "/adminQnaDetailAjax.omc?BD_NUM="+commandMap.get("BC_NUM"));
		adminComService.deleteComment(commandMap.getMap());				
		
		return mv;
	}
	
	@RequestMapping(value="/adminQnaComDeleteAjax.omc")
	@ResponseBody
	public String adminQnaComDeleteAjax(CommandMap commandMap) throws Exception {

		adminComService.deleteComment(commandMap.getMap());				
		
		return "good";
	}
}