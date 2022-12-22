package omc.common.board;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO{
	
	///공지조회
	@SuppressWarnings("unchecked") 
	public Map<String, Object> noticeListPagingm(Map<String, Object>map) throws Exception{
		return (Map<String, Object>) selectPagingList("board.noticeListPagingm", map);
		
		
	}
	//faq조회
	@SuppressWarnings("unchecked")
	public Map<String, Object> faqListPagingm(Map<String, Object>map) throws Exception{
		return (Map<String, Object>) selectPagingList("board.faqListPagingm", map);
	}
	
	//커뮤니티 조회
	@SuppressWarnings("unchecked")
	public Map<String, Object> boardListPaging(Map<String, Object>map) throws Exception{
	   return (Map<String, Object>) selectPagingList("board.boardListPaging", map);
		   }
	
	//커뮤니티, faq, 공지읽기/디테일
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectNoticeId(Map<String, Object>map) {
		return (List<Map<String,Object>>) selectList("board.selectBoardId",map);
	}
	//조회수
	public void updateHitCnt(Map<String, Object> map){
	      update("board.updateHitCnt", map);
	   }
	//커뮤니티 글작성
	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("board.insertBoard", map);
	}
	
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
	   
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> board(Map<String, Object> map){
//		return (List<Map<String, Object>>) selectList("board.board",map);
//	}
//	
//	//ReviewDAO 내용
//	// 리뷰 작성
//	@SuppressWarnings("unchecked")
//	public void reviewUserWrite(Map<String, Object> map) {
//		insert("review.reviewUserWrite", map);
//	}
//	
//	// 리뷰 작성 페이지 이동 	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getOrder(Map<String, Object> map){
//		return (List<Map<String, Object>>) selectList("review.getOrder",map);
//	}
//	
//	// 리뷰 상세보기 페이지 이동 	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> reviewDetail(Map<String, Object> map){
//		return (List<Map<String, Object>>) selectList("review.reviewDetail",map);
//	}
//		
//	// 관리자 답변 작성
//	@SuppressWarnings("unchecked")
//	public void reviewReWrite(Map<String, Object> map) {
//		insert("review.reviewReWrite", map);
//	}
//	
//	// 관리자 답변 상세
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> reviewReDetail(Map<String, Object> map){
//		return (List<Map<String, Object>>) selectList("review.reviewReDetail",map);
//	}
//	
//	// 관리자 답변 삭제
//	public void reviewReDelete(Map<String, Object> map) throws Exception {
//		delete("review.reviewReDelete", map);	
//	}
//	
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

}
