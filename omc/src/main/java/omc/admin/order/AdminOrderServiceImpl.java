package omc.admin.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("adminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService{

	@Resource(name="adminOrderDAO")
	private AdminOrderDAO adminOrderDAO;
	
	@Override
	public List<Map<String, Object>> orderList() throws Exception {
		return adminOrderDAO.orderList();
	}

	@Override
	public List<Map<String, Object>> orderListSearch(String condition, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", condition);
		map.put("KEYWORD", keyword);		
		
		return adminOrderDAO.orderListSearch(map);
	}

	@Override
	public Map<String, Object> selectOrderOId(Map<String, Object> map) throws Exception {
		return adminOrderDAO.selectOrderOId(map);
	}

	@Override
	public void updateOrderDirect(Map<String, Object> map) throws Exception {
		adminOrderDAO.updateOrderDirect(map);
	}

	/* 주문 수량 구하기 */
	@Override
	public int orderCount() throws Exception {
		Map<String,Object> mapCount = adminOrderDAO.orderCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
	
	//주문 리스트 페이징
	@Override
	public List<Map<String, Object>> orderListPaging(int START, int END) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
		
		 map.put("START", START);
		 map.put("END", END);
		
		return adminOrderDAO.orderListPaging(map);
	}
	
	// 주문 검색
	@Override
	public int orderSearchCount(String CONDITION, String KEYWORD) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", CONDITION);
		map.put("KEYWORD", KEYWORD);		
		
		Map<String, Object> countMap = adminOrderDAO.orderSearchCount(map);
		
		return Integer.parseInt(String.valueOf(countMap.get("COUNT")));
	}
	
	// 주문 리스트 검색 페이징
	@Override
	public List<Map<String, Object>> orderListSearchPaging(String CONDITION, String KEYWORD, int START, int END)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", CONDITION);
		map.put("KEYWORD", KEYWORD);
		map.put("START", START);
		map.put("END", END);
		
		return adminOrderDAO.orderListSearchPaging(map);
	}
}