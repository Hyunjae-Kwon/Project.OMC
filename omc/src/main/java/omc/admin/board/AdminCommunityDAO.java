package omc.admin.board;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;
import omc.common.common.CommandMap;

@Repository("adminCommunityDAO")
public class AdminCommunityDAO extends AbstractDAO {
	
	Logger log = Logger.getLogger(this.getClass());
	 	
	//관리자 공지 게시판 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> noticeList() throws Exception {
		return (List<Map<String, Object>>) selectList("board.noticeList");
	}
		
	//관리자 공지 게시판 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectNoticeId(Map<String, Object> map) throws Exception {
		return  (Map<String, Object>) selectOne("board.selectNoticeId", map);
	}
		
	//관리자 공지사항 글 등록
	@SuppressWarnings("unchecked")
	public void insertNotice(Map<String,Object> map) throws Exception{
		insert("board.insertNotice",map);
	}
		
	//관리자 공지사항 수정 
	@SuppressWarnings("unchecked")
	public void updateNoticeId(Map<String,Object> map) throws Exception{
		update("board.updateNoticeId",map);
	}
	
	//관리자 고객후기 게시판 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>reviewList() throws Exception {
		return (List<Map<String, Object>>) selectList("board.reviewList");
	}
		
	//관리자 고객센터 게시판 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>qnaList() throws Exception {
		return (List<Map<String, Object>>) selectList("board.qnaList");
	}
		
	//관리자 고객센터 게시판 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectQnaId(Map<String, Object> map) throws Exception {
		return  (Map<String, Object>) selectOne("board.selectQnaId", map);
	}
		
	//관리자 고객센터 댓글 보기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>commentListId(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("board.commentListId", map);
	}
	
	//관리자 고객센터 댓글 입력
	@SuppressWarnings("unchecked")
	public void insertComment(Map<String, Object> map) throws Exception{
		insert("board.insertComment",map);
	}
	
	//관리자 고객센터 댓글 수정
	@SuppressWarnings("unchecked")
	public void updateComment(Map<String, Object> map) throws Exception {
		update("board.updateComment", map);
	}

	//관리자 고객센터 댓글 삭제
	@SuppressWarnings("unchecked")
	public void deleteComment(Map<String, Object> map) throws Exception {
		delete("board.deleteComment", map);
	}
	
	//관리자 공지,후기 삭제 기능
	@SuppressWarnings("unchecked")
	public void deleteCommunityId(Map<String, Object> map) throws Exception {
		delete("board.deleteCommunityId", map);
	}
	
	/* 페이징 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> noticeListPaging(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("board.noticeListPaging", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>reviewListPaging(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("board.reviewListPaging", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>qnaListPaging(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("board.qnaListPaging", map);
	}	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> noticeListCount() throws Exception {
		return (Map<String, Object>) selectOne("board.noticeListCount");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> reviewListCount() throws Exception {
		return (Map<String, Object>) selectOne("board.reviewListCount");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> qnaListCount() throws Exception {
		return (Map<String, Object>) selectOne("board.qnaListCount");
	}
}