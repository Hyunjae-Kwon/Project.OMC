package omc.admin.board;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.common.CommandMap;

@Controller
public class AdminFaqController {
	
	@Resource(name = "adminCommunityService")
	private AdminCommunityService adminComService;
	
	//faq리스트 관리자
	@RequestMapping(value = "/adminFaqList.omc")
	public ModelAndView adminFaqList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/community/adminFaqList");
		
		Map<String, Object> resultMap = adminComService.adminFaqListPaging(commandMap.getMap());
		
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("faqList", resultMap.get("result"));
		
	return mv;
	}
	
	//디테일
	@RequestMapping(value = "/adminFaqDetail.omc")
	public ModelAndView boardDetail1(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminFaqDetail");
		List<Map<String, Object>> boardDetail = adminComService.selectFaqId(commandMap.getMap());

		HttpSession session = request.getSession();
		String MEM_ID = (String)session.getValue("MEM_ID");
		mv.addObject("boardDetail", boardDetail);
	
		session.setAttribute("MEM_ID", MEM_ID);
		
		return mv;
	}
	
	//faq 글쓰기
	
	@RequestMapping(value="/adminFaqWriteForm.omc")
	public ModelAndView openBoardWrite(CommandMap commandMap , HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("admin/community/adminFaqWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/adminFaqWrite.omc", method = RequestMethod.POST)
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/adminFaqList.omc");
		
		adminComService.insertBoard(commandMap.getMap());
		
		HttpSession session = request.getSession();
		String BD_ID = (String) session.getValue("MEM_ID");
		session.setAttribute("BD_ID",BD_ID);
		return mv;
	}
	
	//faq 글수정폼
		@RequestMapping(value="/adminUpdateFaqForm.omc") 
		public ModelAndView adminupdateBoardForm(CommandMap commandMap) throws Exception{
			ModelAndView mv = new ModelAndView("admin/community/adminFaqModify");
			Map<String, Object> map = adminComService.updateBoardForm(commandMap.getMap());
			
			mv.addObject("board", map);
		            
			return mv;
		}
		//faq 글수정 완성
		@RequestMapping(value="/adminFaqUpdate.omc")
		public ModelAndView adminupdateBoard(CommandMap commandMap)throws Exception{
			ModelAndView mv = new ModelAndView("redirect:/adminFaqList.omc");
			adminComService.updateBoard(commandMap.getMap());
			
			return mv;
		}
	
		//faq 글삭제
		@RequestMapping(value = "/adminFaqDelete.omc")
		public ModelAndView boardDelete(CommandMap commandMap) throws Exception {
			ModelAndView mv = new ModelAndView("redirect:/adminFaqList.omc");
			adminComService.deleteBoard(commandMap.getMap());
			      
			return mv;      
		}
}
