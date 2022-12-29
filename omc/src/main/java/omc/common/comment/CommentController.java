package omc.common.comment;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import omc.common.common.CommandMap;

@Controller
public class CommentController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commentService")
	private CommentService commentService;
	 
	@RequestMapping(value="/boardComWrite.omc")
	public String boardComWrite(CommandMap commandMap,Model model) throws Exception {	

		model.addAttribute("msg", "댓글 작성이 완료되었습니다.");
		model.addAttribute("url", "/boardDetail.omc?BD_NUM="+commandMap.get("BC_NUM"));
		commentService.insertBoardComment(commandMap.getMap());		
	         
		return "/board/boardComWrite";
	}

	//댓글삭제
	@RequestMapping(value="/commentDelete.omc")
	public String commentDelete(CommandMap commandMap, Model model) 
			throws Exception {

		
		model.addAttribute("msg", "댓글 삭제가 완료되었습니다.");
		model.addAttribute("url", "/boardDetail.omc?BD_NUM="+commandMap.get("BC_NUM"));
		commentService.deleteComment(commandMap.getMap());				
		
		return "/board/commentDelete";
	}
	
	//댓글수정
	@RequestMapping(value="/commentModify.omc")
	public String adminQnaComModify(CommandMap commandMap, Model model) throws Exception {

		model.addAttribute("msg", "댓글 수정이 완료되었습니다.");
		model.addAttribute("url", "/boardDetail.omc?BD_NUM="+commandMap.get("BC_NUM"));
		commentService.updateComment(commandMap.getMap());				
		
		return "/board/commentModify";
	}
}