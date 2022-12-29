package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface GoodsService {
	
	/* 전체 상품 리스트 */
	Map<String, Object> allGoodsList(Map<String, Object> map) throws Exception;
	
	/* 신상품 리스트 */
	List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception;
	
	/* 베스트 상품 리스트 */
	List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception;
	
	/* 카테고리 상품 리스트 페이징 */
	Map<String, Object> categoryGoodsListPaging(Map<String, Object> map) throws Exception;
	
	/* 상품 상세 정보 */
	Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception;
	
	/* 상품 검색 */
	List<Map<String, Object>> searchGoodsList(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	/* 상품 리뷰 리스트 (상품 상세) */
	List<Map<String, Object>> goodsReview(Map<String, Object> map) throws Exception;
	
	/* 상품 문의 리스트 (상품 상세) */
	List<Map<String, Object>> goodsQna(Map<String, Object> map) throws Exception;
	
	/* 판매 수량 재고 업데이트 (재고 감소) */
	void sellCountUpdate(Map<String, Object> map) throws Exception;
	
	/* 판매 수량 업데이트 (판매량 증가) */
	void saleCountUpdate(Map<String, Object> map) throws Exception;
	
	/* 주문 취소 수량 재고 업데이트 (재고 증가) */
	void sellCountUpdateC(Map<String, Object> map) throws Exception;
	
	/* 주문 취소 수량 판매량 업데이트 (판매량 감소) (판매량 감소) */
	void saleCountUpdateC(Map<String, Object> map) throws Exception;
	
	/* 상품 전체 수량 구하기 */
	int allGoodsCount() throws Exception;
	
}