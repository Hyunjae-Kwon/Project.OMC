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
	
	@SuppressWarnings("unchecked")
	public void cartPut(Map<String, Object> map){
	 insert("cart.cartPut",map);
	}
	
	@SuppressWarnings("unchecked")
	public void cartDelete(Map<String, Object> map){
	 insert("cart.cartDelete",map);
	}

}
