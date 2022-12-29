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
	
	/* 내 장바구니 리스트 */
	@Override
	public List<Map<String, Object>> selectCartList(String loginId) throws Exception {
		return cartDAO.selectCartList(loginId);
	}
	
	/* 장바구니 수량 변경 */
	@Override
	public void updateMyCart(Map<String, Object> map) throws Exception {
		cartDAO.updateMyCart(map);
	}
	
	/* 선택상품 장바구니 삭제 */
	@Override
	public void delSelectMyCart(Map<String, Object> map) throws Exception {
		cartDAO.delSelectMyCart(map);
	}
	
	/* 장바구니 구매 상품 리스트 */
	@Override
	public List<Map<String, Object>> cartDetail(String loginId) throws Exception {
		return cartDAO.cartDetail(loginId);
	}
	
	/* 상품 장바구니에 저장 */
	@Override
	public int insertCart(Map<String, Object> map) {
		if(0 < cartDAO.findCart(map)) {
			return 0;
		}
		return cartDAO.addCart(map);
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
