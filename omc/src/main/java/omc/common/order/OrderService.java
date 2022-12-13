package omc.common.order;

import java.util.List;
import java.util.Map;

public interface OrderService {
	
	List<Map<String, Object>> insertOrderDirect(Map<String, Object> map);
	
	List<Map<String, Object>> insertOrderCart(Map<String, Object> map);
	
	/* 마이페이지 주문조회 */
	List<Map<String, Object>> myOrderList(Map<String, Object> map);

	List<Map<String, Object>> selectOrderOId(Map<String, Object> map);

	public int selectOIDMax() throws Exception;	 

}