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
	   
//	@Autowired
//	FileService fileService;

	@RequestMapping(value = "/noticeList.omc")
	public ModelAndView noticeList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/noticeList");
		
		Map<String, Object> resultMap = boardService.noticeListPaging(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("noticeList", resultMap.get("result"));
		
		return mv;
	}
	

	@RequestMapping(value = "/faqList.omc")
	public ModelAndView faqList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("board/faqList");
		
		Map<String, Object> resultMap = boardService.faqListPaging(commandMap.getMap());
		// HttpSession session = request.getSession(true);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("faqList", resultMap.get("result"));
		
		return mv;
	}
	
	
	   //커뮤니티
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
		List<Map<String, Object>> boardDetail = boardService.selectNoticeId(commandMap.getMap());
		
		HttpSession session = request.getSession();
		String MEM_ID = (String)session.getValue("MEM_ID");
		mv.addObject("boardDetail", boardDetail);
		session.setAttribute("MEM_ID", MEM_ID);
		
		return mv;
	}
	
	@RequestMapping(value = "/boardDetail.omc")
	public ModelAndView boardDetail(CommandMap commandMap, Model model,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("board/boardDetail");
		List<Map<String, Object>> boardDetail = boardService.selectNoticeId(commandMap.getMap());
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
	

	@RequestMapping(value="/boardWrite.omc")
	public ModelAndView openBoardWrite(CommandMap commandMap , HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardWrite");
//		
//		HttpSession session = request.getSession();
//		String MEM_ID = (String) session.getValue("MEM_ID");
//		session.setAttribute("MEM_ID", MEM_ID);
		return mv;
	}
	@RequestMapping(value="/insertBoard.omc", method = RequestMethod.POST)
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/boardList.omc");
		
		boardService.insertBoard(commandMap.getMap());
		
		HttpSession session = request.getSession();
		String BD_ID = (String) session.getValue("MEM_ID");
		session.setAttribute("BD_ID",BD_ID);
		return mv;
	}
	
//커뮤니티 수정
	  @RequestMapping(value="/updateBoardForm.omc") //보드 수정폼
	   public ModelAndView updateBoardForm(CommandMap commandMap) throws Exception{
	      ModelAndView mv = new ModelAndView("/board/boardModify");
	      Map<String, Object> map = boardService.updateBoardForm(commandMap.getMap());
	      mv.addObject("board", map);
	            
	      return mv;
	   }
	   
	   @RequestMapping(value="/updateBoard.omc") //수정 완성
	   public ModelAndView updateBoard(CommandMap commandMap)throws Exception{
	      ModelAndView mv = new ModelAndView("redirect:/boardList.omc");
	      boardService.updateBoard(commandMap.getMap());
	      return mv;
	   }
	   
	   //커뮤니티 삭제
	   @RequestMapping(value = "/boardDelete.omc")
	   public ModelAndView boardDelete(CommandMap commandMap) throws Exception {
	      ModelAndView mv = new ModelAndView("redirect:/boardList.omc");
	      boardService.deleteBoard(commandMap.getMap());
	      
	      return mv;      
	   }
	
//	   @RequestMapping(value="/boardWrite.omc")
//	    public ModelAndView boardWrite(CommandMap commandMap)throws Exception{
//	         ModelAndView mv = new ModelAndView("board/boardWrite");
//	            
//	         return mv;
//	   }
//	      
//	   @RequestMapping(value="/insertBoard.omc") //글작성
//	    public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
//	      ModelAndView mv = new ModelAndView("redirect:noticeList.omc");
//	      commandMap.put("BOARD_IMAGE", request.getSession().getAttribute("GOODS_IMG_THUM"));
//	      boardService.insertBoardWrite(commandMap.getMap(), request);
//	      Map<String,Object> board = boardService.insertBoardWrite(commandMap.getMap(),request);
//	         mv.addObject("board", board);   
//	      mv.addObject("BD_NUM", commandMap.get("BD_NUM"));
//	         
//	      return mv;
//	   }
//
//	@RequestMapping(value = "/board/boardWrite.omc", method = RequestMethod.GET)
//	public ModelAndView boardWriteForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("board/boardWriteForm");
//
//		return mv;
//	}
//
//	@RequestMapping(value = "/board/boardWrite.omc", method = RequestMethod.POST)
//	public ModelAndView boardWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
//
//		ModelAndView mv = new ModelAndView("board/board");
//		 if (log.isDebugEnabled()) {
//	            log.debug(commandMap);
//	        }
//		fileService.insertBoard(commandMap.getMap(), request);
//
//		return mv;
//	}
//	
//	//reviewController 내용
//	
//	// 리뷰 작성 페이지 이동
//	@RequestMapping(value = "/reviewWritePage")
//	public ModelAndView reviewWritePage(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("reviewWritePage");
//		List<Map<String, Object>> reviewWritePage = boardService.getOrder(commandMap.getMap());
//		HttpSession session = request.getSession();// 세션 값 불러오고
//		String USER_ID = (String) session.getValue("USER_ID");// 값을 String 저장하고
//		mv.addObject("reviewWritePage", reviewWritePage);
//		session.setAttribute("USER_ID", USER_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
//		
//		return mv;
//	}
//	

//	// 리뷰 상세 페이지 이동
//	@RequestMapping(value = "/reviewDetail")
//	public ModelAndView reviewDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("reviewDetail");
//		// HttpSession session = request.getSession();
//		// String REVIEW_INDEX = (String)session.getValue("REVIEW_INDEX");
//		// session.setAttribute("REVIEW_INDEX", REVIEW_INDEX);
//		// commandMap.put("REVIEW_INDEX", session.getAttribute("REVIEW_INDEX"));
//		
//		List<Map<String, Object>> reviewDetail = boardService.reviewDetail(commandMap.getMap());
//		List<Map<String, Object>> reviewReDetail = boardService.reviewReDetail(commandMap.getMap());
//		mv.addObject("reviewDetail", reviewDetail);
//		mv.addObject("reviewReDetail", reviewReDetail);
//		
//		return mv;
//	}
//	
//	// 리뷰 작성
//	@RequestMapping(value="/review/userWrite" , method = RequestMethod.POST)
//	@ResponseBody
//	public ModelAndView reviewUserWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("jsonView");
//		boardService.reviewUserWrite(commandMap.getMap(), request);
//		
//		return mv;
//	}
//	
//	// 관리자 답변 작성
//	@RequestMapping(value= "/reviewReWrite")
//	public ModelAndView reviewReWrite(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("reviewDetail");
//		boardService.reviewReWrite(commandMap.getMap());
//			
//		return mv;
//	}
//	
//	// 관리자 답변 삭제
//	@RequestMapping(value="/reviewReDelete" )
//	public ModelAndView reviewReDelete(CommandMap commandMap, HttpServletRequest request) throws Exception{
//		ModelAndView mv = new ModelAndView("reviewDetail");		
//		boardService.reviewReDelete(commandMap.getMap());
//		
//		return mv;
//	}

}
