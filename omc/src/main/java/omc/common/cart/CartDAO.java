package omc.common.cart;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("cartDAO")
public class CartDAO  extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cartList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("cart.cartList",map);
	}
	
	public void cartPut(Map<String, Object> map){
	 insert("cart.cartPut",map);
	}
	
	public void cartDelete(Map<String, Object> map){
	 insert("cart.cartDelete",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCartGID(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("cart.selectCartGID", map);
	}
	
	/* 상품 등록 */
	public void insertCart(Map<String, Object> map) throws Exception {
		insert("cart.insertCart", map);
	}
	
	public int addCart(Map<String, Object> map) {
		return (Integer) insert("cart.addCart",map);
	}
	
	public int findCart(Map<String, Object> map) {
		return (Integer) selectOne("cart.findCart",map);
	}
	
	/* 장바구니 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCartList(String loginId) throws Exception{
		return (List<Map<String, Object>>) selectList ("cart.selectCartList", loginId);
	}
	
	/* 장바구니 구매 상품 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cartDetail(String loginId) throws Exception{
		return (List<Map<String, Object>>) selectList ("cart.cartDetail", loginId);
	}
	
	/* 선택상품 장바구니 삭제 */
	public void delSelectMyCart(Map<String, Object> map) throws Exception {
		delete("cart.deleteMyCart", map);
	}
	
	/* 장바구니 수량 변경 */
	public void updateMyCart(Map<String, Object> map) throws Exception {
		update("cart.updateMyCart", map);
	}
	
	/* 장바구니 구매 상품 카트 번호 부여 */
	public void updateNum(Map<String, Object> map) throws Exception {
		update("cart.updateNum", map);
	}
	
	/* 선택상품 장바구니 삭제 */
	public void delCartOrder(String loginId) throws Exception {
		delete("cart.delCartOrder", loginId);
	}	
}