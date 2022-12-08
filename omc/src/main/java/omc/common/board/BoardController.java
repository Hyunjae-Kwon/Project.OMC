package omc.common.board;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;
import omc.common.file.FileService;
import omc.common.file.FileUploadController;

@Controller
public class BoardController {
	private static final Logger log = Logger.getLogger(FileUploadController.class);

	@Resource(name = "boardService")
	BoardService boardService;
	@Autowired
	FileService fileService;

	@RequestMapping(value = "/board.omc")
	public ModelAndView board(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/board");
		List<Map<String, Object>> board = boardService.board(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("board", board);
		// session.getAttribute("USER_ID");

		return mv;
	}

	@RequestMapping(value = "/board/boardWrite.omc", method = RequestMethod.GET)
	public ModelAndView boardWriteForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardWriteForm");

		return mv;
	}

	@RequestMapping(value = "/board/boardWrite.omc", method = RequestMethod.POST)
	public ModelAndView boardWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("board/board");
		 if (log.isDebugEnabled()) {
	            log.debug(commandMap);
	        }
		fileService.insertBoard(commandMap.getMap(), request);

		return mv;
	}
	
	//reviewController 내용
	
	// 리뷰 작성 페이지 이동
	@RequestMapping(value = "/reviewWritePage")
	public ModelAndView reviewWritePage(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("reviewWritePage");
		List<Map<String, Object>> reviewWritePage = boardService.getOrder(commandMap.getMap());
		HttpSession session = request.getSession();// 세션 값 불러오고
		String USER_ID = (String) session.getValue("USER_ID");// 값을 String 저장하고
		mv.addObject("reviewWritePage", reviewWritePage);
		session.setAttribute("USER_ID", USER_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
		
		return mv;
	}
	
	// 리뷰 상세 페이지 이동
	@RequestMapping(value = "/reviewDetail")
	public ModelAndView reviewDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("reviewDetail");
		// HttpSession session = request.getSession();
		// String REVIEW_INDEX = (String)session.getValue("REVIEW_INDEX");
		// session.setAttribute("REVIEW_INDEX", REVIEW_INDEX);
		// commandMap.put("REVIEW_INDEX", session.getAttribute("REVIEW_INDEX"));
		
		List<Map<String, Object>> reviewDetail = boardService.reviewDetail(commandMap.getMap());
		List<Map<String, Object>> reviewReDetail = boardService.reviewReDetail(commandMap.getMap());
		mv.addObject("reviewDetail", reviewDetail);
		mv.addObject("reviewReDetail", reviewReDetail);
		
		return mv;
	}
	
	// 리뷰 작성
	@RequestMapping(value="/review/userWrite" , method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView reviewUserWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		boardService.reviewUserWrite(commandMap.getMap(), request);
		
		return mv;
	}
	
	// 관리자 답변 작성
	@RequestMapping(value= "/reviewReWrite")
	public ModelAndView reviewReWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("reviewDetail");
		boardService.reviewReWrite(commandMap.getMap());
			
		return mv;
	}
	
	// 관리자 답변 삭제
	@RequestMapping(value="/reviewReDelete" )
	public ModelAndView reviewReDelete(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("reviewDetail");		
		boardService.reviewReDelete(commandMap.getMap());
		
		return mv;
	}

}
