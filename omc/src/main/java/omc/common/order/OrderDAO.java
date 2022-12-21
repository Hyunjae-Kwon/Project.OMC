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
	@SuppressWarnings("unchecked")
	public int insertOrderCart(Map<String, Object> map) {
		return (int) insert("order.insertOrderCart", map);
	}
	
	/* 주문 결과 확인 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOrderOID(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("order.selectOrderOId", map);
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> insertOrderDirect(Map<String, Object> map) {
//		return (List<Map<String, Object>>) selectList("order.insertOrderDirect", map);
//	}
	
	/* 마이페이지 주문조회 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("order.selectOrderMemberId", map);
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
}
