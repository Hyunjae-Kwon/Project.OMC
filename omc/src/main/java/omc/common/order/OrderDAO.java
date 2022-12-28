package omc.common.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("orderDAO")
public class OrderDAO extends AbstractDAO {
	
	/* 주문하기 (상품 상세 -> 주문하기) */
	@SuppressWarnings("unchecked")
	public Map<String, Object> insertOrderDirect(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("order.insertOrderDirect", map);
	}
	
	/* 주문하기 (장바구니 -> 주문하기) */
	public int insertOrderCart(Map<String, Object> map) {
		return (int) insert("order.insertOrderCart", map);
	}
	
	/* 주문 결과 확인 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOrderOID(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("order.selectOrderOId", map);
	}
	
	/* 총 결제 금액 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTotalPay(int orderNum) {
		return (List<Map<String, Object>>) selectList("order.selectTotalPay", orderNum);
	}

	/* 마이페이지 주문조회 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> myOrderListPaging(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectPagingList("order.myOrderList", map);
	}	
	
	/* 마이페이지 주문 상세 */
	@SuppressWarnings("unchecked")
	   public List<Map<String, Object>> selectOrderODNum(Map<String, Object> map) throws Exception{
	      return (List<Map<String, Object>>) selectList("order.selectOrderODNum", map);
	}
	
	/* 마이페이지 주문 결제정보 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectPayODNum(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("order.selectPayODNum", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectOrderOId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("order.selectOrderOId", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOIDMax() throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("order.selectOIDMax");
	}
	
	/* 주문번호 최대값 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectODNumMax() throws Exception {
		return (Map<String, Object>) selectOne("order.selectODNumMax");
	}
	
	/* 결제 정보 입력 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> insertPay(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("order.insertPay", map);
	}
	
	/* 주문 결과 확인 (장바구니 -> 주문하기) */
	@SuppressWarnings("unchecked")
	   public List<Map<String, Object>> selectOrderODNum(int orderNum) throws Exception{
	      return (List<Map<String, Object>>) selectList("order.selectOrderODNum", orderNum);
	}
	
	/* 결제 정보 확인 (장바구니 -> 주문하기) */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectPayODNum(int orderNum) throws Exception{
		return (Map<String, Object>) selectOne("order.selectPayODNum", orderNum);
	}
	
	/* 마이페이지 주문 취소 */
	public void deleteOrder(Map<String, Object> map) throws Exception {
		delete("order.deleteOrder", map);
	}
	
	/* 마이페이지 주문 취소 결제 정보 삭제 */
	public void deletePay(Map<String, Object> map) throws Exception {
		delete("order.deletePay", map);
	}
}