package omc.common.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {
	
	/* 주문하기 (상품 상세 -> 주문하기) */
	Map<String, Object> insertOrderDirect(Map<String, Object> map, HttpServletRequest request);
	
	/* 주문하기 (장바구니 -> 주문하기) */
	public int insertOrderCart(Map<String, Object> map);
	
	/* 주문 결과 확인 */
	Map<String, Object> selectOrderOID(Map<String, Object> map, HttpServletRequest request);
	
	/* 마이페이지 주문 조회 */
	Map<String, Object> myOrderListPaging(Map<String, Object> map) throws Exception;
	
	/* 마이페이지 주문 상세 */
   	List<Map<String, Object>> selectOrderODNum(Map<String, Object> map) throws Exception;
   	
   	/* 마이페이지 주문 결제정보 */
   	Map<String, Object> selectPayODNum(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectOrderOid(Map<String, Object> map);

	public int selectOIDMax() throws Exception;	 
	
	/* 주문번호 최대값 */
	public int selectODNumMax() throws Exception;
	
	/* 결제 정보 입력 */
	Map<String, Object> insertPay(Map<String, Object> map);
	
	/* 주문 결과 확인 (장바구니 -> 주문하기) */
   	List<Map<String, Object>> selectOrderODNum(int orderNum) throws Exception;   
   	
   	/* 결제 정보 확인 (장바구니 -> 주문하기) */
   	Map<String, Object> selectPayODNum(int orderNum) throws Exception;
   	
   	/* 마이페이지 주문 취소 */
   	void deleteOrder(Map<String, Object> map) throws Exception;
   	
   	/* 마이페이지 주문 취소 결제 정보 삭제 */
   	void deletePay(Map<String, Object> map) throws Exception;
}