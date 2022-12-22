package omc.admin.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.util.Paging;

@Controller
public class AdminNoticeController {

	@Resource(name = "adminCommunityService")
	private AdminCommunityService adminComService;

	@RequestMapping(value = "/adminNoticeList.omc", method = RequestMethod.GET)
	public ModelAndView adminNoticeList(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("admin/community/adminNoticeListAjax");
		/* 페이징을 위한 변수 */
		int pageSize = 20; // 페이지당 출력할 공지의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int noticeListCount; // 전체 공지글의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminNoticeList.omc";
		String searchUrl = "";

		List<Map<String, Object>> list = adminComService.noticeListPaging(START, END);
		List<Map<String, Object>> noticeListArr = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> noticeList = adminComService.noticeList();

		for (Map<String, Object> mapObject : list) {
			noticeListArr.add(mapObject);
		}

		noticeListCount = adminComService.noticeListCount();

		Paging paging = new Paging(noticeListCount, pageBlock, pageSize, currentPage, url, searchUrl);

		/* 페이징을 위한 값 삽입 */
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);

		mv.addObject("noticeListArr", noticeListArr);
		mv.addObject("noticeList", noticeList);

		return mv;
	}

	@RequestMapping(value = "/adminNoticeWriteForm.omc")
	public ModelAndView adminNoticeWriteForm(CommandMap commandMap) {
		 ModelAndView mv = new  ModelAndView("admin/community/adminNoticeWriteForm");
		 
		return mv;
	}

	@RequestMapping(value = "/adminNoticeWrite.omc")
	public ModelAndView adminNoticeWrite(CommandMap commandMap) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/community/adminNoticeWrite");

		mv.addObject("msg", "게시글 작성이 완료되었습니다.");
		mv.addObject("url", "/adminNoticeList.omc");
		adminComService.insertNotice(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/adminNoticeModifyForm.omc")
	public ModelAndView adminNoticeModifyForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/community/adminNoticeModifyForm");

		Map<String, Object> noticeMap = adminComService.selectNoticeId(commandMap.getMap());

		mv.addObject("noticeMap", noticeMap);

		return mv;
	}

	@RequestMapping(value = "/adminNoticeModify.omc")
	public ModelAndView adminNoticeModify(CommandMap commandMap) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/community/adminNoticeModify");

		adminComService.updateNoticeId(commandMap.getMap());
		mv.addObject("msg", "게시글 수정이 완료되었습니다.");
		mv.addObject("url", "/adminNoticeList.omc");

		return mv;
	}

	@RequestMapping(value = "/adminNoticeDelete.omc")
	public ModelAndView adminNoticeDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/community/adminNoticeDelete");

		mv.addObject("msg", "게시글 삭제가 완료되었습니다.");
		mv.addObject("url", "/adminNoticeList.omc");

		adminComService.deleteCommunityId(commandMap.getMap());
		
		return mv;
	}

	@RequestMapping(value = "/adminNoticeDeleteAjax.omc")
	public ModelAndView adminNoticeDeleteAjax(CommandMap commandMap) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/community/adminNoticeList");

		adminComService.deleteCommunityId(commandMap.getMap());

		return mv;
	}

	@RequestMapping(value = "/adminNoticeDetail.omc")
	public ModelAndView adminNoticeDetail(CommandMap commandMap) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/community/adminNoticeDetail");

		Map<String, Object> noticeMap = adminComService.selectNoticeId(commandMap.getMap());

		noticeMap = adminComService.selectNoticeId(commandMap.getMap());

		mv.addObject("noticeMap", noticeMap);

		return mv;
	}

}