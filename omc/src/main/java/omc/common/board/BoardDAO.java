package omc.common.board;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO{
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> board(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("board.board",map);
	}
	
	//ReviewDAO 내용
	// 리뷰 작성
	@SuppressWarnings("unchecked")
	public void reviewUserWrite(Map<String, Object> map) {
		insert("review.reviewUserWrite", map);
	}
	
	// 리뷰 작성 페이지 이동 	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrder(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("review.getOrder",map);
	}
	
	// 리뷰 상세보기 페이지 이동 	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> reviewDetail(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("review.reviewDetail",map);
	}
		
	// 관리자 답변 작성
	@SuppressWarnings("unchecked")
	public void reviewReWrite(Map<String, Object> map) {
		insert("review.reviewReWrite", map);
	}
	
	// 관리자 답변 상세
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> reviewReDetail(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("review.reviewReDetail",map);
	}
	
	// 관리자 답변 삭제
	public void reviewReDelete(Map<String, Object> map) throws Exception {
		delete("review.reviewReDelete", map);	
	}
	
	/* 마이페이지 리뷰 조회 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myReviewList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("board.selectReviewMemberId", map);
	}
	
	/* 마이페이지 Qna 조회 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myQnaList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("board.selectQnaMemberId", map);
	}
	///공지조회
	@SuppressWarnings("unchecked") 
	public Map<String, Object> noticeListPaging(Map<String, Object>map) throws Exception{
		return (Map<String, Object>) selectPagingList("board.noticeListPaging", map);
		
		
	}
	//qna조회
	@SuppressWarnings("unchecked")
	public Map<String, Object> faqListPaging(Map<String, Object>map) throws Exception{
		return (Map<String, Object>) selectPagingList("board.faqListPaging", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectNoticeId(Map<String, Object>map) {
		return (List<Map<String,Object>>) selectList("board.selectNoticeId",map);
	}
	
	public void updateHitCnt(Map<String, Object> map){
	      update("board.updateHitCnt", map);
	   }
	
	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("board.insertBoard", map);
	}
	
	 @SuppressWarnings("unchecked")
	   public Map<String, Object> boardListPaging(Map<String, Object>map) throws Exception{
	      return (Map<String, Object>) selectPagingList("board.boardListPaging", map);
	   }
//
//	public void insertNotice(Map<String, Object> map) throws Exception{
//	      insert("board.insertNotice", map);
//	   }	
	//커뮤니티 수정
	   @SuppressWarnings("unchecked")
	   public Map<String, Object> updateBoardForm(Map<String, Object> map)throws Exception{
	      return (Map<String, Object>)selectOne("board.updateBoardForm", map); 
	   }
	   
	   public void updateBoard(Map<String, Object> map)throws Exception{
	      update("board.updateBoard", map);
	   }
	   
	//커뮤니티 글삭제
	   public void deleteBoard(Map<String, Object> map) throws Exception {
	         delete("board.deleteBoard", map);
	      }
}
