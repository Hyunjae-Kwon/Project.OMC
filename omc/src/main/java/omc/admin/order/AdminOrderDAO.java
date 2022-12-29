package omc.admin.order;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("adminOrderDAO")
public class AdminOrderDAO extends AbstractDAO{

	Logger log = Logger.getLogger(this.getClass());
	
	/* 주문 수량 구하기 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> orderCount() throws Exception {
		return (Map<String, Object>) selectOne("order.orderCount");
	}
	
	/* 검색된 주문 수 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> orderSearchCount(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("order.orderSearchCount", map);
	}
	
	/* 주문 리스트 페이징 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderListPaging(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("order.orderListPaging", map);
	}	
	
	/* 주문 검색 페이징 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderListSearchPaging(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("order.orderListSearchPaging", map);
	}
	
	/* 주문리스트 상세보기 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectOrderOId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("order.selectOrderOId", map);
	}
	
	/* 주문 정보 수정 기능 */
	public void updateOrderDirect(Map<String, Object> map) throws Exception {
		update("order.updateOrderDirect", map); 
	}
	
	/* 전체 상품 리스트, 매출 리스트 - 판매량을 조건으로 정렬하기 위해 상품 리스트 조건 검색 페이징 */
    @SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsListPaging(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectPagingList("goods.selectGoodsListPaging", map);
	}

    /* 매출 리스트 - 매출 총합 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> sellSum(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("goods.sellSum", map);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//주문리스트 	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderList() throws Exception {
		return (List<Map<String, Object>>) selectList("order.orderList");
	}
	//주문리스트 검색
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> orderListSearch(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("order.orderListSearch", map);
	}
	
}