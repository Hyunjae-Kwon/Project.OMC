package omc.common.comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
	
	public List<Map<String, Object>> selectCommentList(Map<String, Object> map)throws Exception;
	
	public void insertBoardComment(Map<String, Object> map)throws Exception;
	//댓글삭제
	public void deleteComment(Map<String, Object> map) throws Exception;
	//댓글수정
	public void updateComment(Map<String, Object> map)throws Exception;
}