package omc.common.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	
	//공지리스트
	Map<String,Object> noticeListPagingm(Map<String, Object>map) throws Exception;
	
	//faq리스트
	Map<String,Object> faqListPagingm(Map<String, Object>map) throws Exception;
	
	//커뮤니티 리스트
	Map<String,Object> boardListPaging(Map<String, Object>map) throws Exception;

	//공지,faq,커뮤니티 상세보기
	List<Map<String, Object>> selectBoardId(Map<String, Object>map) ;
	
	//커뮤니티 글쓰기
	void insertBoard(Map<String, Object> map) throws Exception;
	
	//커뮤니티 수정
	 Map<String, Object> updateBoardForm(Map<String, Object> map)throws Exception;
	 
	 void updateBoard(Map<String, Object> map)throws Exception;
	 
	 //커뮤니티글삭제
	 void deleteBoard(Map<String, Object> map) throws Exception;
	 
	/* 마이페이지 리뷰 조회 */
	List<Map<String, Object>> myReviewList(Map<String, Object> map);
	
	/* 마이페이지 Qna 조회 */
	List<Map<String, Object>> myQnaList(Map<String, Object> map);
	
	/* 상품 후기 작성 */
	void insertReview(Map<String, Object> map) throws Exception;

	/* 상품 문의 작성 */
	void insertQna(Map<String, Object> map) throws Exception;

}
