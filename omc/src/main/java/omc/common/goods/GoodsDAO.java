package omc.common.goods;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("goodsDAO")
public class GoodsDAO extends AbstractDAO {

	//경고무시 9. unchecked : 검증되지 않은 연산자 관련 경고 억제 
	//구독판매 페이지
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsSubList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectPagingList("goods.goodsSubList",map);
	}
	//일반판매 페이지
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsSaleList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectPagingList("goods.goodsSaleList",map);
	}
	
	//구독 상세 페이징
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> subDetail(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.SubDetail",map);
	}
	// 일반 상세 페이징
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> saleDetail(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.SaleDetail",map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> review(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.Review",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> review2(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.Review2",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> reviewRe(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.ReviewRe",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSaleThumb(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("goods.getSaleThumb",map);
	}
	
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
	
	/* 상품 상세 정보에서 장바구니로 넘어갈 때 같이 전송하는 상품 정보 */
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> selectGoods(String memberId) throws Exception {
//		return (Map<String, Object>) selectOne("goods.selectGoodsId", memberId);
//	}
	
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
	
	/* 상품 수정 폼*/
	@SuppressWarnings("unchecked")
	public Map<String, Object> goodsModifyForm(Map<String, Object> map){
		return (Map<String, Object>) selectList("goods.goodsModify",map);
	}
	
	/* 상품 수정 */
	public void goodsModify(Map<String, Object> map) throws Exception {
		insert("goods.goodsModify", map);
	}
	
	/* 상품 삭제 */
	public void goodsDelete(Map<String, Object> map) throws Exception {
		delete("goods.goodsDelete", map);
	}
	
	/* 상품 등록 */
	public void goodsWrite(Map<String, Object> map) throws Exception {
		insert("goods.goodsWrite", map);
	}
	
	/* 상품 등록 (이미지만 업데이트) */
	public void updateImg(Map<String, Object> map) throws Exception{
		update("goods.updateImg", map);
	}
	
	/* 상품 마지막 번호 구하기 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGD_GIDMax() throws Exception{
		return (Map<String, Object>)selectOne("goods.selectGD_GIDMax");
	}
	
	/* 상품 수정 */
	public void sellCountUpdate(Map<String, Object> map) throws Exception {
		update("goods.sellCountUpdate", map);
	}
	
//	subList()구독판매페이지
//	saleList()일반판매페이지
//	subDetail()구독판매상세페이지
//	saleDetail()일반판매상세페이지
	//goodsListDetail 리스트 상세
}
