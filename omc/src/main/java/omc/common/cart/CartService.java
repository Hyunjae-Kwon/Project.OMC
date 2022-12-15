package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import omc.common.common.CommandMap;

public interface CartService {

	List<Map<String, Object>> cartList(Map<String, Object> map);
	
	void cartPut(Map<String, Object> map);
	
	void cartDelete(Map<String, Object> map);
	
	List<Map<String, Object>> selectCartGID(Map<String, Object> map);
	
	/* 상품 장바구니에 저장 */
	void insertCart(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	int insertCart(Map<String, Object> map);
	
	/* 장바구니 리스트 */
	List<Map<String, Object>> selectCartList(String loginId) throws Exception;
	
}
