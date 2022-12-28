package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
	/* 장바구니 구매 상품 리스트 */
	List<Map<String, Object>> cartDetail(String loginId) throws Exception;
	
	/* 선택상품 장바구니 삭제 */
	void delSelectMyCart(Map<String, Object> map) throws Exception;
	
	/* 장바구니 수량 변경 */
	void updateMyCart(Map<String, Object> map) throws Exception;
	
	/* 장바구니 구매 상품 카트 번호 부여 */
	void updateNum(Map<String, Object> map) throws Exception;
	
	/* 장바구니 구매 상품 구매 완료 시 장바구니 삭제 */
	void delCartOrder(String loginId) throws Exception;	
}