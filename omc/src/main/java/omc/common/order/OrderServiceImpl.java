package omc.common.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import omc.common.goods.GoodsDAO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDAO")
	private OrderDAO orderDAO;
	
	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;

	/* 주문하기 (상품 상세 -> 주문하기) */
	@Override
	public Map<String, Object> insertOrderDirect(Map<String, Object> map, HttpServletRequest request) {
		return orderDAO.insertOrderDirect(map);
	}
	
	/* 주문하기 (장바구니 -> 주문하기) */
	@Override
	public int insertOrderCart(Map<String, Object> map) {
		return orderDAO.insertOrderCart(map);
	}
	
	/* 주문 결과 확인 */
	@Override
	public Map<String, Object> selectOrderOID(Map<String, Object> map, HttpServletRequest request) {
		return orderDAO.selectOrderOID(map);
	}
	
	/* 총 결제 금액 */
	@Override
	public List<Map<String, Object>> selectTotalPay(int orderNum) {
		return orderDAO.selectTotalPay(orderNum);
	}

	/* 마이페이지 주문조회 */
	@Override
	public List<Map<String, Object>> myOrderList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.myOrderList(map);
	}


	@Override
	public List<Map<String, Object>> selectOrderOid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDAO.selectOrderOId(map);
	}
	
	/* 단품상품 구매 시 사용하는 OID 최대값 */
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

	/* 주문번호 최대값 */
	@Override
	public int selectODNumMax() throws Exception {
		Map<String, Object> maxMap = new HashMap<String, Object>();
		maxMap = orderDAO.selectODNumMax();
		
		int maxODNum;
		if(maxMap == null) {
			maxODNum = 0;
		} else {
			maxODNum = Integer.parseInt(String.valueOf(maxMap.get("MAX")));
		}
				
		return maxODNum;
	}

	/* 결제 정보 입력 */
	@Override
	public Map<String, Object> insertPay(Map<String, Object> map) {
		return orderDAO.insertPay(map);
	}
	
	  /* 주문 결과 확인 (장바구니 -> 주문하기) */
	   @Override
	   public List<Map<String, Object>> selectOrderODNum(int orderNum) throws Exception {
	      return orderDAO.selectOrderODNum(orderNum);
	   }
	
}