package omc.common.comment;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Resource(name="commentDAO")
	private CommentDAO commentDAO;
	
	@Override
	public List<Map<String, Object>> selectCommentList(Map<String, Object> map) throws Exception {
		
		map.put("BC_NUM", map.get("BC_NUM"));
		
		return commentDAO.selectCommentList(map);
	}
	
	@Override
	public void insertBoardComment(Map<String, Object> map) throws Exception {
		
		map.put("BC_NUM", map.get("BC_NUM"));
		map.put("BC_ID", map.get("BC_ID"));
		map.put("BC_COMMENT", map.get("BC_COMMENT"));
		
		commentDAO.insertBoardComment(map);
	}
	//댓글삭제
	@Override
	public void deleteComment(Map<String, Object> map) throws Exception {
		commentDAO.deleteComment(map);
	}
	 //댓글수정
	@Override
	public void updateComment(Map<String, Object> map) throws Exception {
			
		map.put("BC_COMMENT", map.get("BC_COMMENT"));
		map.put("BC_BCID", map.get("BC_BCID"));
			
		commentDAO.updateComment(map);
	}
}