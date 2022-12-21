package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import omc.util.FileUtils;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Override
	public List<Map<String, Object>> maingoods(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return goodsDAO.maingoods(map);
	}

	@Override
	public List<Map<String, Object>> getReview2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return goodsDAO.review2(map);
	}

	@Override
	public List<Map<String, Object>> getReviewRe(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return goodsDAO.reviewRe(map);
	}

	@Override
	public List<Map<String, Object>> getReview(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return goodsDAO.review(map);
	}


	@Override
	public List<Map<String, Object>> goodsSubList(Map<String, Object> map) {
		return goodsDAO.goodsSubList(map);
	}

	@Override
	public List<Map<String, Object>> goodsSaleList(Map<String, Object> map) {
		return goodsDAO.goodsSaleList(map);
	}

	@Override
	public List<Map<String, Object>> getsubDetail(Map<String, Object> map) {
		return goodsDAO.subDetail(map);
	}

	@Override
	public List<Map<String, Object>> getsaleDetail(Map<String, Object> map) {
		return goodsDAO.saleDetail(map);
	}
	
	/* 전체 상품 리스트 */
	@Override
	public Map<String, Object> allGoodsList(Map<String, Object> map) throws Exception {
		return goodsDAO.allGoodsList(map);
	}
	
	/* 신상품 리스트 */
	@Override
	public List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception {
		return goodsDAO.newGoodsList(map);
	}
	
	/* 베스트 상품 리스트 */
	@Override
	public List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception {
		return goodsDAO.bestGoodsList(map);
	}
	
	/* 카테고리 상품 리스트 */
	@Override
	public List<Map<String, Object>> categoryGoodsList(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> resultMap = goodsDAO.categoryGoodsList(map);
		
		return resultMap; 
	}
	
	/* 상품 상세 정보 */
	@Override
	public Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = goodsDAO.goodsDetail(map);
		return resultMap;
	}
	
	/* 장바구니에서 주문하기로 넘어가는 상품 상세 정보 */
	@Override
	public List<Map<String, Object>> goodsDetailCart(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> resultMap = goodsDAO.goodsDetailCart(map);
		return resultMap;
	}
	
	/* 상품 상세 정보에서 장바구니로 넘어갈 때 같이 전송하는 상품 정보 */
//	@Override
//	public Map<String, Object> selectGoods(String memberId) throws Exception {
//		return goodsDAO.selectGoods(memberId);
//	}
	
	/* 상품 리뷰 리스트 (상품 상세) */
	@Override
	public List<Map<String, Object>> goodsReview(Map<String, Object> map) throws Exception {
		return goodsDAO.goodsReview(map);
	}

	/* 상품 문의 리스트 (상품 상세) */
	@Override
	public List<Map<String, Object>> goodsQna(Map<String, Object> map) throws Exception {
		return goodsDAO.goodsQna(map);
	}
	
	/* 상품 수정 폼 */
	@Override
	public Map<String, Object> goodsModifyForm(Map<String, Object> map) {
		return goodsDAO.goodsModifyForm(map);
	}
	
	/* 상품 수정 */
	@Override
	public void goodsModify(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		goodsDAO.goodsModify(map);
	}
	
	/* 상품 삭제 */
	@Override
	public void goodsDelete(Map<String, Object> map, HttpServletRequest request) throws Exception {
		goodsDAO.goodsDelete(map);
	}
	
	/* 상품 등록 */
	@Override
	public void goodsWrite(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		goodsDAO.goodsWrite(map);
		
		Map<String,Object> map1 = fileUtils.parseInsertFileInfo(map, request);
		goodsDAO.updateImg(map1);
	}

	/* 구매 수량 재고 업데이트 */
	@Override
	public void sellCountUpdate(Map<String, Object> map) throws Exception {
		goodsDAO.sellCountUpdate(map);
	}
	
}
