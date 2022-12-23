package omc.common.goods;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("goodsDAO")
public class GoodsDAO extends AbstractDAO {
	
	//메인 페이지
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> maingoods(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.maingoods",map);
	}
	
	/* 전체 상품 리스트 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> allGoodsList(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectPagingList("goods.allGoodsList", map);
	}
	
	/* 신상품 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.newGoodsList", map);
	}
	
	/* 베스트 상품 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.bestGoodsList", map);
	}
	
	/* 카테고리 상품 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> categoryGoodsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.categoryGoodsList", map);
	}
	
	/* 카테고리 상품 리스트 페이징 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> categoryGoodsListPaging(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectPagingList("goods.categoryGoodsList", map);
	}
	
	/* 상품 상세 정보 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne ("goods.goodsDetail", map);
	}
	
	/* 장바구니에서 주문하기로 넘어가는 상품 상세 정보 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsDetailCart(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.goodsDetailCart", map);
	}
	
	/* 상품 리뷰 리스트 (상품 상세) */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsReview(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("goods.goodsReview",map);
	}
	
	/* 상품 문의 리스트 (상품 상세) */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsQna(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("goods.goodsQna",map);
	}
	
	/* 상품 마지막 번호 구하기 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGD_GIDMax() throws Exception{
		return (Map<String, Object>)selectOne("goods.selectGD_GIDMax");
	}
	
	/* 구매 수량에 따른 상품 재고 수정 */
	public void sellCountUpdate(Map<String, Object> map) throws Exception {
		update("goods.sellCountUpdate", map);
	}
	
	/* 판매 수량 업데이트 */
	public void saleCountUpdate(Map<String, Object> map) throws Exception {
		update("goods.saleCountUpdate", map);
	}
	
	/* 상품 전체 수량 구하기 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> allGoodsCount() throws Exception {
		return (Map<String, Object>) selectOne("goods.allGoodsCount");
	}
	
	/* 상품 검색 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> searchGoodsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.searchGoodsList", map);
	}
}
