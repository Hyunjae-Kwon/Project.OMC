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

	/* 상품 후기 작성 */
	public void insertReview(Map<String, Object> map) throws Exception {
		insert("board.insertReview", map);
	}

	/* 상품 문의 작성 */
	public void insertQna(Map<String, Object> map) throws Exception {
		insert("board.insertQna", map);
	}

}
