package omc.common.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.comment.CommentService;
import omc.common.common.CommandMap;
//import omc.common.file.FileService;
//import omc.common.file.FileUploadController;

@Controller
public class BoardController {
	//	private static final Logger log = Logger.getLogger(FileUploadController.class);

	@Resource(name = "boardService")
	BoardService boardService;

	@Resource(name="commentService")
	private CommentService commentService;

	//공지 리스트
	@RequestMapping(value = "/noticeList.omc")
	public ModelAndView noticeList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/noticeList");

		Map<String, Object> resultMap = boardService.noticeListPagingm(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("noticeList", resultMap.get("result"));

		return mv;
	}

	//faq 리스트
	@RequestMapping(value = "/faqList.omc")
	public ModelAndView faqList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/faqList");

		Map<String, Object> resultMap = boardService.faqListPagingm(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("faqList", resultMap.get("result"));

		return mv;
	}
	
	//커뮤니티리스트
	@RequestMapping(value = "/boardList.omc")
	public ModelAndView boardList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardList");

		Map<String, Object> resultMap = boardService.boardListPaging(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("boardList", resultMap.get("result"));

		return mv;
	}

	//공지, faq상세보기
	@RequestMapping(value = "/boardDetail1.omc")
	public ModelAndView boardDetail1(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardDetail1");
		List<Map<String, Object>> boardDetail = boardService.selectBoardId(commandMap.getMap());
		List<Map<String, Object>> comList = commentService.selectCommentList(commandMap.getMap());

		HttpSession session = request.getSession();
		String MEM_ID = (String)session.getValue("MEM_ID");
		mv.addObject("boardDetail", boardDetail);
		mv.addObject("comList",comList);
		session.setAttribute("MEM_ID", MEM_ID);

		return mv;
	}

	//커뮤니티상세보기 /댓글리스트 
	@RequestMapping(value = "/boardDetail.omc")
	public ModelAndView boardDetail(CommandMap commandMap, Model model,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardDetail");
		List<Map<String, Object>> boardDetail = boardService.selectBoardId(commandMap.getMap());
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

	//커뮤니티 글쓰기폼
	@RequestMapping(value="/boardWrite.omc")
	public ModelAndView openBoardWrite(CommandMap commandMap , HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardWrite");

		return mv;
	}

	//커뮤니티글쓰기
	@RequestMapping(value="/insertBoard.omc", method = RequestMethod.POST)
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/boardList.omc");

		boardService.insertBoard(commandMap.getMap());

		HttpSession session = request.getSession();
		String BD_ID = (String) session.getValue("MEM_ID");
		session.setAttribute("BD_ID",BD_ID);
		return mv;
	}

	//커뮤니티 글수정폼
	@RequestMapping(value="/updateBoardForm.omc") 
	public ModelAndView updateBoardForm(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardModify");
		Map<String, Object> map = boardService.updateBoardForm(commandMap.getMap());

		mv.addObject("board", map);

		return mv;
	}
	//커뮤니티 글수정 완성
	@RequestMapping(value="/updateBoard.omc")
	public ModelAndView updateBoard(CommandMap commandMap)throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/boardList.omc");
		boardService.updateBoard(commandMap.getMap());

		return mv;
	}

	//커뮤니티글삭제
	@RequestMapping(value = "/boardDelete.omc")
	public ModelAndView boardDelete(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/boardList.omc");
		boardService.deleteBoard(commandMap.getMap());

		return mv;      
	}
}
