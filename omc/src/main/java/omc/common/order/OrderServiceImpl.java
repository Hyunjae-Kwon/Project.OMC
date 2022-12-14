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
	
//	@Override
//	public List<Map<String, Object>> selectOrderOid(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return orderDAO.selectOrderOId(map);
//	}
	
	/* 주문하기 폼 (장바구니 -> 주문하기) 주문번호 최대값 */
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
	
	/* 결제 정보 입력 */
	@Override
	public Map<String, Object> insertPay(Map<String, Object> map) {
		return orderDAO.insertPay(map);
	}
	
	/* 단품상품 구매 시 사용하는 OID 최대값 -> 주문 결과 호출용 */
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
	
	/* 주문 결과 확인 (상품 상세 -> 주문하기) */
	@Override
	public Map<String, Object> selectOrderOID(Map<String, Object> map, HttpServletRequest request) {
		return orderDAO.selectOrderOID(map);
	}
	
	/* 주문 결과 확인 (장바구니 -> 주문하기) */
	@Override
	public List<Map<String, Object>> selectOrderODNum(int orderNum) throws Exception {
		return orderDAO.selectOrderODNum(orderNum);
	}
	
	/* 결제 정보 확인 (장바구니 -> 주문하기) */
	@Override
	public Map<String, Object> selectPayODNum(int orderNum) throws Exception {
		return orderDAO.selectPayODNum(orderNum);
	}
	
	//주문하기//////////////////////////////////////////////////////////////////////////////////////
	
	/* 마이페이지 주문조회 */
	@Override
	public Map<String, Object> myOrderListPaging(Map<String, Object> map) throws Exception{
		return orderDAO.myOrderListPaging(map);
	}
	
	/* 마이페이지 주문 상세 */
	@Override
	public List<Map<String, Object>> selectOrderODNum(Map<String, Object> map) throws Exception{
		return orderDAO.selectOrderODNum(map);
	}
	
	/* 마이페이지 주문 결제정보 */
	@Override
	public Map<String, Object> selectPayODNum(Map<String, Object> map) throws Exception{
		return orderDAO.selectPayODNum(map);
	}
	
	/* 마이페이지 주문 취소 */
	@Override
	public void deleteOrder(Map<String, Object> map) throws Exception {
		orderDAO.deleteOrder(map);
	}

	/* 마이페이지 주문 취소 결제 정보 삭제 */
	@Override
	public void deletePay(Map<String, Object> map) throws Exception {
		orderDAO.deletePay(map);
	}	
}