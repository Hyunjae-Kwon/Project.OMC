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

	/* 상품 수정 */
	public void adminGoodsModify(Map<String, Object> map) throws Exception {
		insert("goods.adminGoodsModify", map);
	}
	
	/* 상품 삭제 */
	public void adminGoodsDelete(Map<String, Object> map) throws Exception {
		delete("goods.adminGoodsDelete", map);
	}
	
}