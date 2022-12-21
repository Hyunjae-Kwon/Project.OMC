package omc.common.comment;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;


@Repository("commentDAO")
public class CommentDAO extends AbstractDAO{
	

	Logger log = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>selectCommentList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("comment.selectCommentList", map);
	}

	@SuppressWarnings("unchecked")
	public void insertBoardComment(Map<String, Object> map) throws Exception{
		insert("comment.insertBoardComment",map);
	}
	//댓삭
	public void deleteComment(Map<String, Object> map) throws Exception {
		delete("comment.deleteComment", map);	
	}
	//댓수정
	@SuppressWarnings("unchecked")
	public void updateComment(Map<String, Object> map) throws Exception {
		update("comment.updateComment", map);
	}
}
