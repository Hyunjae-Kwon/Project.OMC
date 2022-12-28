package omc.admin.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.comment.CommentService;
import omc.common.common.CommandMap;

@Controller
public class AdminCommunityController {
	
	@Resource(name = "adminCommunityService")
	AdminCommunityService adminCommunityService;
	
	@Resource(name="commentService")
	private CommentService commentService;
	
	/* 관리자 커뮤니티 리스트 */
	@RequestMapping(value = "/adminBoardList.omc")
	public ModelAndView adminBoardListPaging(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/community/adminBoardList");
		
		Map<String, Object> resultMap = adminCommunityService.adminBoardListPaging(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("boardList", resultMap.get("result"));
		
		return mv;
	}
	
	/* 관리자 커뮤니티 상세보기 (조회수 증가 X) */
	@RequestMapping(value = "/adminBoardDetail.omc")
	public ModelAndView boardDetail(CommandMap commandMap, Model model,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminBoardDetail");
		List<Map<String, Object>> boardDetail = adminCommunityService.adminBoardDetail(commandMap.getMap());
		commandMap.put("BC_NUM", commandMap.get("BD_NUM"));
		List<Map<String, Object>> commentList = commentService.selectCommentList(commandMap.getMap());
		List<Map<String, Object>> comList = new ArrayList<Map<String, Object>>();
		for(Map<String, Object> mapObject : commentList) {
			comList.add(mapObject);
		}
		HttpSession session = request.getSession();
		String MEM_ID = (String)session.getValue("MEM_ID");
		mv.addObject("boardDetail", boardDetail);
		session.setAttribute("MEM_ID", MEM_ID);
		model.addAttribute("comList", comList);	
		model.addAttribute("commentList", commentList);			
		
		return mv;
	}
	
	/* 관리자 커뮤니티 수정 폼 */
	@RequestMapping(value="/adminBoardModifyForm.omc") 
	public ModelAndView adminBoardModifyForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminBoardModify");
		Map<String, Object> map = adminCommunityService.adminBoardModifyForm(commandMap.getMap());
		
		mv.addObject("board", map);
	            
		return mv;
	}
	
	/* 관리자 커뮤니티 수정 기능 */
	@RequestMapping(value="/adminBoardModify.omc")
	public ModelAndView adminBoardModify(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminBoardList.omc");
		adminCommunityService.adminBoardModify(commandMap.getMap());
		
		return mv;
	}
	
	/* 관리자 커뮤니티 삭제 */
	@RequestMapping(value = "/adminBoardDelete.omc")
	public ModelAndView adminBoardDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/adminBoardList.omc");
		adminCommunityService.adminBoardDelete(commandMap.getMap());
		      
		return mv;      
	}
}