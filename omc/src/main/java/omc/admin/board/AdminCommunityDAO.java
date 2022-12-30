package omc.admin.board;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("adminCommunityDAO")
public class AdminCommunityDAO extends AbstractDAO {
	
	Logger log = Logger.getLogger(this.getClass());
	
	/* 고객 후기 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>reviewList() throws Exception {
		return (List<Map<String, Object>>) selectList("board.reviewList");
	}
	
	/* 고객 후기 리스트 (페이징) */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>reviewListPaging(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("board.reviewListPaging", map);
	}
	
	/* 고객 후기 리스트 (페이징을 위한 수량 구하기) */
	@SuppressWarnings("unchecked")
	public Map<String, Object> reviewListCount() throws Exception {
		return (Map<String, Object>) selectOne("board.reviewListCount");
	}
	
	/* 고객 후기 삭제 */
	public void deleteCommunityId(Map<String, Object> map) throws Exception {
		delete("board.deleteCommunityId", map);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	 	
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
	public void insertNotice(Map<String,Object> map) throws Exception{
		insert("board.insertNotice",map);
	}
		
	//관리자 공지사항 수정 
	public void updateNoticeId(Map<String,Object> map) throws Exception{
		update("board.updateNoticeId",map);
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
	public void insertComment(Map<String, Object> map) throws Exception{
		insert("board.insertComment",map);
	}

	//관리자 고객센터 댓글 삭제
	public void deleteComment(Map<String, Object> map) throws Exception {
		delete("board.deleteComment", map);
	}
	
	/* 페이징 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> noticeListPaging(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("board.noticeListPaging", map);
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
	public Map<String, Object> qnaListCount() throws Exception {
		return (Map<String, Object>) selectOne("board.qnaListCount");
	}
	
	//관리자 faq리스
	@SuppressWarnings("unchecked")
	public Map<String, Object> adminFaqListPaging(Map<String, Object>map) throws Exception{
		return (Map<String, Object>) selectPagingList("board.adminFaqListPaging", map);
	}
	
	//faq디테일
		@SuppressWarnings("unchecked")
		public List<Map<String, Object>> selectFaqId(Map<String, Object>map) {
			return (List<Map<String,Object>>) selectList("board.selectFaqId",map);
		}
		
		public void updateHitCnt(Map<String, Object> map){
		      update("board.updateHitCnt", map);
		   }
	//faq 글작성
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
	
	//faq 글삭제
   public void deleteBoard(Map<String, Object> map) throws Exception {
         delete("board.deleteBoard", map);
      }
	   
   /* 관리자 커뮤니티 리스트 */
   @SuppressWarnings("unchecked")
   public Map<String, Object> adminBoardListPaging(Map<String, Object> map) throws Exception{
	   return (Map<String, Object>) selectPagingList("board.adminBoardListPaging", map);
   }
   
   /* 관리자 커뮤니티 상세보기 (조회수 증가 X) */
   @SuppressWarnings("unchecked")
	public List<Map<String, Object>> adminBoardDetail(Map<String, Object> map) {
		return (List<Map<String,Object>>) selectList("board.selectBoardId",map);
	}
   
   /* 관리자 커뮤니티 수정 폼 */
   @SuppressWarnings("unchecked")
   public Map<String, Object> adminBoardModifyForm(Map<String, Object> map)throws Exception{
      return (Map<String, Object>)selectOne("board.updateBoardForm", map); 
   }
   
   /* 관리자 커뮤니티 수정 기능 */
   public void adminBoardModify(Map<String, Object> map)throws Exception{
      update("board.updateBoard", map);
   }
   
   /* 관리자 커뮤니티 삭제 */
   public void adminBoardDelete(Map<String, Object> map) throws Exception {
       delete("board.deleteBoard", map);
   }
}