package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface GoodsService {
	
	/* 전체 상품 리스트 */
	Map<String, Object> allGoodsList(Map<String, Object> map) throws Exception;
	
	/* 카테고리 상품 리스트 페이징 */
	Map<String, Object> categoryGoodsListPaging(Map<String, Object> map) throws Exception;
	
	/* 신상품 리스트 */
	List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception;
	
	/* 베스트 상품 리스트 */
	List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception;
	
	/* 카테고리 상품 리스트 */
	List<Map<String, Object>> categoryGoodsList(Map<String, Object> map) throws Exception;
	
	/* 상품 상세 정보 */
	Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception;
	
	/* 장바구니에서 주문하기로 넘어가는 상품 상세 정보 */
	List<Map<String, Object>> goodsDetailCart(Map<String, Object> map) throws Exception;
	
	/* 상품 리뷰 리스트 (상품 상세) */
	List<Map<String, Object>> goodsReview(Map<String, Object> map) throws Exception;
	
	/* 상품 문의 리스트 (상품 상세) */
	List<Map<String, Object>> goodsQna(Map<String, Object> map) throws Exception;
	
	/* 구매 수량 재고 업데이트 */
	void sellCountUpdate(Map<String, Object> map) throws Exception;
	
	/* 상품 전체 수량 구하기 */
	int allGoodsCount() throws Exception;
	
	/* 상품 검색 */
	List<Map<String, Object>> searchGoodsList(Map<String, Object> map, HttpServletRequest request) throws Exception;
}
