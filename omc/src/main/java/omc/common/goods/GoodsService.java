package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	/* 카테고리 상품 리스트 */
	List<Map<String, Object>> categoryGoodsList(Map<String, Object> map) throws Exception; //카테고리
	
	/* 상품 상세 정보 */
	Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception;
	
	/* 장바구니에서 주문하기로 넘어가는 상품 상세 정보 */
	List<Map<String, Object>> goodsDetailCart(Map<String, Object> map) throws Exception;
	
	/* 상품 상세 정보에서 장바구니로 넘어갈 때 같이 전송하는 상품 정보 */
//	public Map<String, Object> selectGoods(String memberId) throws Exception;
	
	/* 상품 리뷰 리스트 (상품 상세) */
	List<Map<String, Object>> goodsReview(Map<String, Object> map) throws Exception;
	
	/* 상품 문의 리스트 (상품 상세) */
	List<Map<String, Object>> goodsQna(Map<String, Object> map) throws Exception;
	
	/* 상품 수정 폼 */
	Map<String, Object> goodsModifyForm(Map<String, Object> map);
	
	/* 상품 수정 */
	void goodsModify(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception;
	
	/* 상품 삭제 */
	void goodsDelete(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	/* 상품 등록 */
	void goodsWrite(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception;
	
	/* 구매 수량 재고 업데이트 */
	void sellCountUpdate(Map<String, Object> map) throws Exception;
	
	/* 주문하기 (장바구니 -> 주문하기) */
//	public int insertOrderCart(Map<String, Object> map);

	
//	subList()구독판매페이지
//	saleList()일반판매페이지
//	subDetail()구독판매상세페이지
//	saleDetail()일반판매상세페이지
// goodsListDetail 리스트 상세
}
