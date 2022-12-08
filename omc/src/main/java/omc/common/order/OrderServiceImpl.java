package omc.common.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service ("orderService")
public class OrderServiceImpl  implements OrderService{
	
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;

	@Override
	public List<Map<String, Object>> orderMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.orderMember(map);
	}

	@Override
	public List<Map<String, Object>> orderPayPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  orderDAO.orderPage(map);
	}

	@Override
	public List<Map<String, Object>> orderPay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.orderPay(map);
	}

	@Override
	public List<Map<String, Object>> orderPayPage2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  orderDAO.orderPage2(map);
	}

	@Override
	public List<Map<String, Object>> orderPay2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.orderPay2(map);
	}
	
	/* 마이페이지 주문조회 */
	@Override
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.myOrderList(map);
	}

}
