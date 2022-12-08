package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service ("cartService")
public class CartServiceImpl implements CartService{

	@Resource(name="cartDAO")
	private CartDAO cartDAO;
	
	@Override
	public List<Map<String, Object>> cartList(Map<String, Object> map) {
		return cartDAO.cartList(map);
	}

	@Override
	public void cartPut(Map<String, Object> map) {
		cartDAO.cartPut(map);
	}

	@Override
	public void cartDelete(Map<String, Object> map) {
		cartDAO.cartDelete(map);
	}

}
