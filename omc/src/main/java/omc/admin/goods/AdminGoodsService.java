package omc.admin.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
 
public interface AdminGoodsService {
	//관리자 상품 리스트 페이징
	public List<Map<String, Object>> allListPaging(int START, int END) throws Exception;

	// 상품 리스트
	public List<Map<String, Object>> goodsList() throws Exception;
	
	//관리자 상품 리스트 중 KEYWORD로 검색
	public List<Map<String, Object>> allListKeywordPaging(String KEYWORD, int KEYNUMBER, int START, int END) throws Exception;
	
	//관리자 상품 리스트를 KEYWORD로 검색했을 때의 수 
	public int allListKeywordCount(String KEYWORD, int KEYNUMBER) throws Exception;
	
	// 상품번호로 장바구니 삭제
	public void deleteCartGID(Map<String, Object> map) throws Exception;
	
	//상품 PID최대값 구하기
	public int selectGIDMax() throws Exception;
	
	public Map<String, Object> insertGoods(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception;
	
	/* 상품 등록 */
	void adminGoodsWrite(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception;
	
	/* 상품 수정 폼 */
//	Map<String, Object> goodsModifyForm(Map<String, Object> map);
	
	/* 상품 수정 */
	void adminGoodsModify(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception;
	
	/* 상품 삭제 */
	void adminGoodsDelete(Map<String, Object> map, HttpServletRequest request) throws Exception;
    
}