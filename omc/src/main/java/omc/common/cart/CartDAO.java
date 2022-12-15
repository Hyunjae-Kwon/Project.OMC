package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;
import omc.common.common.CommandMap;

@Repository("cartDAO")
public class CartDAO  extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cartList(Map<String, Object> map){
		return (List<Map<String, Object>>) selectList("cart.cartList",map);
	}
	
	@SuppressWarnings("unchecked")
	public void cartPut(Map<String, Object> map){
	 insert("cart.cartPut",map);
	}
	
	@SuppressWarnings("unchecked")
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
	
}
