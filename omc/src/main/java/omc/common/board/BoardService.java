package omc.common.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	
	List<Map<String, Object>> board(Map<String, Object> map);
	
	//ReviewService 내용
	// 리뷰 작성
	public void reviewUserWrite(Map<String, Object> map, HttpServletRequest request) throws Exception;

	// 리뷰 작성 페이지 이동
	List<Map<String, Object>> getOrder(Map<String, Object> map);
	
	// 리뷰 상세
	List<Map<String, Object>> reviewDetail(Map<String, Object> map);
	
	// 관리자 답변 작성
	void reviewReWrite(Map<String, Object> map) throws Exception;
	
	// 관리자 답변 상세
	List<Map<String, Object>> reviewReDetail(Map<String, Object> map);
	
	// 관리자 답변 삭제
	void reviewReDelete(Map<String, Object> map) throws Exception;
	
	// 파일업로드 테스트
	String restore(MultipartFile multipartFile, String uploadPath,String newFileName) throws Exception;

	/* 마이페이지 리뷰 조회 */
	List<Map<String, Object>> myReviewList(Map<String, Object> map);
	
	/* 마이페이지 Qna 조회 */
	List<Map<String, Object>> myQnaList(Map<String, Object> map);
}
