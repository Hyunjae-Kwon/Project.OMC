package omc.common.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
	
	/* 주문하기 (상품 상세 -> 주문하기) */
	Map<String, Object> insertOrderDirect(Map<String, Object> map, HttpServletRequest request);
	
	/* 주문 결과 확인 */
	Map<String, Object> selectOrderOID(Map<String, Object> map, HttpServletRequest request);
	
	List<Map<String, Object>> insertOrderCart(Map<String, Object> map);
	
	/* 마이페이지 주문조회 */
	List<Map<String, Object>> myOrderList(Map<String, Object> map);

	List<Map<String, Object>> selectOrderOid(Map<String, Object> map);

	public int selectOIDMax() throws Exception;	 

}