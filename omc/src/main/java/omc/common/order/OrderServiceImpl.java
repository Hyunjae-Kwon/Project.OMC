package omc.common.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import omc.common.goods.GoodsDAO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;
	
	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;

	@Override
	public List<Map<String, Object>> insertOrderDirect(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.insertOrderDirect(map);
	}

	@Override
	public List<Map<String, Object>> insertOrderCart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.insertOrderCart(map);
	}
	
	/* 마이페이지 주문조회 */
	@Override
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.myOrderList(map);
	}

	@Override
	public List<Map<String, Object>> selectOrderOId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.selectOrderOId(map);
	}

	@Override
	public int selectOIDMax() throws Exception {
		Map<String, Object> maxMap = new HashMap<String, Object>();
		maxMap = orderDAO.selectOIDMax();
		
		int maxOID;
		if(maxMap == null) {
			maxOID = 0;
		} else {
			maxOID = Integer.parseInt(String.valueOf(maxMap.get("MAX")));
		}
				
		return maxOID;
	}

	
}