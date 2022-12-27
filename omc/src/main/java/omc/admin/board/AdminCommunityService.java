package omc.admin.board;

import java.util.List;
import java.util.Map;

import omc.common.common.CommandMap;

public interface AdminCommunityService {

	public List<Map<String, Object>> noticeList()throws Exception;
	
	public Map<String, Object> selectNoticeId(Map<String, Object> map)throws Exception;
	
	public void insertNotice(Map<String, Object> map)throws Exception;
		
	public void updateNoticeId(Map<String, Object> map)throws Exception;
	
	public List<Map<String, Object>> reviewList()throws Exception;
	
	public List<Map<String, Object>> qnaList()throws Exception;
	
	public Map<String, Object> selectQnaId(Map<String, Object> map)throws Exception;
	
	public List<Map<String, Object>> commentListId(Map<String, Object> map)throws Exception;
	
	public void insertComment(Map<String, Object> map)throws Exception;
	
	public void updateComment(Map<String, Object> map)throws Exception;
	
	public void deleteComment(Map<String, Object> map)throws Exception;
	
	public void deleteCommunityId(Map<String, Object> map)throws Exception;
	
	/* 페이징 */
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception;
	
	public List<Map<String, Object>> reviewListPaging(int START, int END)throws Exception;
	
	public List<Map<String, Object>> qnaListPaging(int START, int END)throws Exception;
	
	public int noticeListCount() throws Exception;
	
	public int qnaListCount() throws Exception;
	
	public int reviewListCount() throws Exception;
	
	//관리자 faq페이징
	Map<String,Object> adminFaqListPaging(Map<String, Object>map) throws Exception;
	
	//faq디테일
	List<Map<String, Object>> selectFaqId(Map<String, Object>map) ;
	
	//faq 글쓰기
	void insertBoard(Map<String, Object> map) throws Exception;
			
	////faq 수정
	 Map<String, Object> updateBoardForm(Map<String, Object> map)throws Exception;
	 
	 void updateBoard(Map<String, Object> map)throws Exception;
	 //faq글삭제
	 void deleteBoard(Map<String, Object> map) throws Exception;
	 	 
	 /* 관리자 커뮤니티 리스트 */
	 Map<String, Object> adminBoardListPaging(Map<String, Object> map) throws Exception;
	 
	 /* 관리자 커뮤니티 상세보기 (조회수 증가 X) */
	 List<Map<String, Object>> adminBoardDetail(Map<String, Object> map);
	 
	 /* 관리자 커뮤니티 수정 폼 */
	 Map<String, Object> adminBoardModifyForm(Map<String, Object> map) throws Exception;
	 
	 /* 관리자 커뮤니티 수정 기능 */
	 void adminBoardModify(Map<String, Object> map) throws Exception;
	 
	 /* 관리자 커뮤니티 삭제 */
	 void adminBoardDelete(Map<String, Object> map) throws Exception;

}
