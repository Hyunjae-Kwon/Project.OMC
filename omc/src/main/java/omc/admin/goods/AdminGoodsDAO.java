package omc.admin.goods;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("adminGoodsDAO")
public class AdminGoodsDAO extends AbstractDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allListPaging(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("goods.allGoodsList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> goodsList() throws Exception {
		return (List<Map<String, Object>>) selectList("goods.goodsList");
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> allListKeywordPaging(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("goods.allListKeywordPaging",map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> allListKeywordCount(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("goods.allgoodsKeywordSearchCount", map);
	}	
    
	public void deleteCartGID (Map<String, Object> map) throws Exception {
		 delete("cart.deleteCartGID", map);
	}
	   
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGIDMax() throws Exception{
		return (Map<String, Object>) selectOne("goods.selectGIDMax");
	}	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> insertGoods(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("goods.goodsWrite", map);
	}
	
	/* 상품 등록 */
	public void adminGoodsWrite(Map<String, Object> map) throws Exception {
		insert("goods.adminGoodsWrite", map);
	}
	
	/* 상품 등록 (이미지만 업데이트) */
	public void adminUpdateImg(Map<String, Object> map) throws Exception{
		update("goods.adminUpdateImg", map);
	}
	
	/* 상품 수정 폼*/
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> goodsModifyForm(Map<String, Object> map){
//		return (Map<String, Object>) selectList("goods.goodsModify",map);
//	}
	
	/* 상품 수정 */
	public void adminGoodsModify(Map<String, Object> map) throws Exception {
		insert("goods.adminGoodsModify", map);
	}
	
	/* 상품 삭제 */
	public void adminGoodsDelete(Map<String, Object> map) throws Exception {
		delete("goods.adminGoodsDelete", map);
	}
	
    /* 매출 총합 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> sellSum(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("goods.sellSum", map);
	}

	/* 전체 상품 리스트, 매출 리스트 */
    @SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsListPaging(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectPagingList("goods.selectGoodsListPaging", map);
	}
    
//    @SuppressWarnings("unchecked")
//	public List<Map<String, Object>> selectGoodsListPaging(Map<String, Object> map) throws Exception{
//		return (List<Map<String, Object>>) selectList("goods.selectGoodsListPaging", map);
//	}
}