package omc.common.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("orderDAO")
public class OrderDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> insertOrderDirect(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("order.insertOrderDirect", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> insertOrderCart(Map<String, Object> map) {
		return (List<Map<String, Object>>) selectList("order.basketOrder", map);
	}
	
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
	
}
