package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface GoodsService {

	List<Map<String, Object>> goodsSubList(Map<String, Object> map);

	List<Map<String, Object>> getReview(Map<String, Object> map);

	List<Map<String, Object>> getReview2(Map<String, Object> map);

	List<Map<String, Object>> getReviewRe(Map<String, Object> map);

	List<Map<String, Object>> goodsSaleList(Map<String, Object> map);

	List<Map<String, Object>> getsubDetail(Map<String, Object> map);

	List<Map<String, Object>> getsaleDetail(Map<String, Object> map);

	List<Map<String, Object>> maingoods(Map<String, Object> map);
	
	/* 전체 상품 리스트 */
	Map<String, Object> allGoodsList(Map<String, Object> map) throws Exception;
	
	/* 신상품 리스트 */
	List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception; //신상
	
	/* 베스트 상품 리스트 */
	List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception; //인기
	
	/* 상품 추가 */
	Map<String, Object> goodsWriteForm(Map<String, Object> map);
	
	void goodsWrite(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
//	subList()구독판매페이지
//	saleList()일반판매페이지
//	subDetail()구독판매상세페이지
//	saleDetail()일반판매상세페이지
// goodsListDetail 리스트 상세
}
