package omc.common.cart;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service ("cartService")
public class CartServiceImpl implements CartService{

	@Resource(name="cartDAO")
	private CartDAO cartDAO;
	
	@Override
	public List<Map<String, Object>> cartList(Map<String, Object> map) {
		return cartDAO.cartList(map);
	}

	@Override
	public void cartPut(Map<String, Object> map) {
		cartDAO.cartPut(map);
	}

	@Override
	public void cartDelete(Map<String, Object> map) {
		cartDAO.cartDelete(map);
	}
	
	@Override
	public List<Map<String, Object>> selectCartGID(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return cartDAO.selectCartGID(map);
	}
	
	/* 상품 장바구니에 저장 */
	@Override
	public void insertCart(Map<String, Object> map, HttpServletRequest request) throws Exception {
		cartDAO.insertCart(map);
	}

	@Override
	public int insertCart(Map<String, Object> map) {
		if(0 < cartDAO.findCart(map)) {
			return 0;
		}
		return cartDAO.addCart(map);
	}

	/* 장바구니 리스트 */
	@Override
	public List<Map<String, Object>> selectCartList(String loginId) throws Exception {
		return cartDAO.selectCartList(loginId);
	}
	
	/* 장바구니 구매 상품 리스트 */
	@Override
	public List<Map<String, Object>> cartDetail(String loginId) throws Exception {
		return cartDAO.cartDetail(loginId);
	}

	/* 선택상품 장바구니 삭제 */
	@Override
	public void delSelectMyCart(Map<String, Object> map) throws Exception {
		cartDAO.delSelectMyCart(map);
	}
	
	/* 장바구니 수량 변경 */
	@Override
	public void updateMyCart(Map<String, Object> map) throws Exception {
		cartDAO.updateMyCart(map);
	}

	/* 장바구니 구매 상품 카트 번호 부여 */
	@Override
	public void updateNum(Map<String, Object> map) throws Exception {
		cartDAO.updateNum(map);
	}

	/* 장바구니 구매 상품 구매 완료 시 장바구니 삭제 */
	@Override
	public void delCartOrder(String loginId) throws Exception {
		cartDAO.delCartOrder(loginId);
	}
	
}
