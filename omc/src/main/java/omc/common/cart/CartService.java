package omc.common.cart;

import java.util.List;
import java.util.Map;

public interface CartService {

	List<Map<String, Object>> cartList(Map<String, Object> map);
	void cartPut(Map<String, Object> map);
	void cartDelete(Map<String, Object> map);

}
