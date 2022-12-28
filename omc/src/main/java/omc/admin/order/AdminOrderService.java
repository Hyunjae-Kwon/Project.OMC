package omc.admin.order;

import java.util.List;
import java.util.Map;

public interface AdminOrderService {
	
	//주문리스트
	public List<Map<String, Object>> orderList() throws Exception;
	    
	//주문리스트 검색
	public List<Map<String, Object>> orderListSearch(String condition, String keyword) throws Exception;
	   
	//주문리스트 상세보기
	public Map<String, Object> selectOrderOId(Map<String, Object> map) throws Exception;
	   
	//주문 정보 수정 기능
	void updateOrderDirect(Map<String, Object> map) throws Exception;
	
	/* 주문 수량 구하기 */
	int orderCount() throws Exception;

	/* 주문 리스트 조건 검색 페이징 */
	public List<Map<String, Object>> orderListPaging(int START, int END) throws Exception;
	
	/* 주문 리스트 검색 입력 */
	int orderSearchCount(String condition, String keyword) throws Exception;
	
	/* 주문리스트 검색 페이징 */
	List<Map<String, Object>> orderListSearchPaging(String condition, String keyword, int sTART, int eND) throws Exception;
}