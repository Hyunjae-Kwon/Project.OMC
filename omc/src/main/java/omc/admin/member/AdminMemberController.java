package omc.admin.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.util.Paging;

@Controller
public class AdminMemberController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "adminMemberService")
	private AdminMemberService adminMemberService;
	
	// 관리자 - 회원 리스트 
	@RequestMapping(value = "/memberList.omc")
	public ModelAndView memberList(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/member/memberList");
		/* 페이징을 위한 변수 */
		int pageSize = 5; // 페이지당 출력할 회원의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countMemberAll; // 전체 회원의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "memberList.omc";
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
//		if(condition!=null && condition.equals("RANK") && keyword!=null && keyword.equals("브론즈")) {
//			keyword = "B";
//		}
//		if(condition!=null && condition.equals("RANK") && keyword!=null && keyword.equals("실버")) {
//			keyword = "S";
//		}
//		if(condition!=null && condition.equals("RANK") && keyword!=null && keyword.equals("골드")) {
//			keyword = "G";
//		}
		
		/* 페이징을 위한 값 계산 */
		if(keyword == null || keyword.trim() =="") { // 검색 조건이 아닐 때
			countMemberAll = adminMemberService.memberCount();
		}else { // 검색어를 입력했을 때
			countMemberAll = adminMemberService.memberSearchCount(condition, keyword);
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소 입력
		Paging paging = new Paging(countMemberAll,	pageBlock,
				pageSize ,currentPage, url, searchUrl);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> memberBeanList = new ArrayList<Map<String, Object>>();
		
		/* 페이징 리스트를 가져옴 */
		if(keyword == null || keyword.trim() =="") {
			list = adminMemberService.memberListPaging(START, END);
		}else {
			list = adminMemberService.memberListSearchPaging(condition, keyword, START, END);
		}
		
		for(Map<String, Object> mapObject : list) {
			memberBeanList.add(mapObject);
		}
		
		mv.addObject("memberBeanList", memberBeanList);
		
		/* 페이징을 위한 값 삽입 */
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);
		
		/* SELECT 태그에서 검색 조건 유지를 위한 값 */
		mv.addObject("condition", condition);
		
		return mv;
	}
	
	// 관리자 - 회원 리스트 상세
//	@RequestMapping(value = "/memberDetail.omc")
//	@ResponseBody
//	public ModelAndView memberDetail(CommandMap commandMap, HttpServletRequest request,Model model) throws Exception {
//		ModelAndView mv = new ModelAndView("admin/member/memberDetail");
//		Map<String, Object> selectMemberId = adminMemberService.selectMemberId(commandMap.getMap());
//		model.addAttribute("selectMemberId", selectMemberId);
//		
//		return mv;
//	}

	@RequestMapping(value = "/memberDetail.omc")
	   public ModelAndView memberDetail (CommandMap commandMap, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("admin/member/memberDetail");
	      Map<String, Object> selectMemberId = new HashMap<String, Object>();
	      
	      selectMemberId = adminMemberService.selectMemberId(commandMap.getMap());

	      mv.addObject("selectMemberId", selectMemberId);
	      
	      return mv;
	   }
	
	// 관리자 - 회원 리스트 수정
	@RequestMapping(value = "/memberModify.omc")
	public ModelAndView memberModify (CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("admin/member/memberModify");
		// BlOCK값이 체크가 되지 않으면 null로 입력되어 넘어오게 된다.
		// 따라서 BlOCK을 하지 않음을 설정하기 위해 "N"값을 입력하여 넘겨준다.
		// 체크가 되었을 경우 "Y"값이 넘어오게 된다.
		if(commandMap.get("MEM_BLOCK") == null) {
			commandMap.put("MEM_BLOCK", "N");
		}
		
		if(commandMap.get("MEM_ID").equals("ADMIN") && commandMap.get("MEM_BLOCK").equals("Y")) {
			mv.addObject("msg", "관리자는 정지시킬 수 없습니다.");
			mv.addObject("url", "/memberList.omc");
			return mv;
		}
		
		adminMemberService.updateMemberAdmin(commandMap.getMap());
		
		mv.addObject("msg", commandMap.get("MEM_ID") + "회원의 정보가 수정되었습니다.");
		mv.addObject("url", "/memberList.omc");
		return mv;
	}

	
	// 관리자 - 회원 리스트 삭제
	@RequestMapping(value = "/memberDelete.omc")
	public ModelAndView memberDelete (CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("admin/member/memberDelete");
		// 선택한 계정을 삭제하기 위해서 파라미터로 계정의 이메일값을 입력해 MemberBean으로 받는다.

		/* ADMIN 계정을 삭제하려고 시도하면 막는다 */
		if(commandMap.get("MEM_ID").equals("ADMIN")) {
			mv.addObject("msg", "관리자는 삭제할 수 없습니다.");
			mv.addObject("url", "/memberDetail.omc?MEM_ID=" + commandMap.get("MEM_ID"));
			
			return mv;	
		}
		
		adminMemberService.deleteMember(commandMap.getMap());
		
		/* 회원 정보 삭제 완료 */
		mv.addObject("msg", commandMap.get("MEM_ID") + "회원이 삭제 되었습니다.");
		mv.addObject("url", "/memberList.omc");
		
		return mv;
	}
	
	
}
