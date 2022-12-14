package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import omc.util.FileUtils;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
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
	
	/* 카테고리 상품 리스트 페이징 */
	@Override
	public Map<String, Object> categoryGoodsListPaging(Map<String, Object> map) throws Exception {
		return goodsDAO.categoryGoodsListPaging(map);
	}
	
	/* 상품 상세 정보 */
	@Override
	public Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = goodsDAO.goodsDetail(map);
		return resultMap;
	}
	
	/* 상품 검색 */
	@Override
	public List<Map<String, Object>> searchGoodsList(Map<String, Object> map, HttpServletRequest request) throws Exception {
		return goodsDAO.searchGoodsList(map);
	}	
	
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

	/* 판매 수량 재고 업데이트 (재고 감소) */
	@Override
	public void sellCountUpdate(Map<String, Object> map) throws Exception {
		goodsDAO.sellCountUpdate(map);
	}
	
	/* 판매 수량 업데이트 (판매량 증가) */
	@Override
	public void saleCountUpdate(Map<String, Object> map) throws Exception {
		goodsDAO.saleCountUpdate(map);
	}
	
	/* 주문 취소 수량 재고 업데이트 (재고 증가) */
	@Override
	public void sellCountUpdateC(Map<String, Object> map) throws Exception {
		goodsDAO.sellCountUpdateC(map);
	}
	
	/* 주문 취소 수량 판매량 업데이트 (판매량 감소) (판매량 감소) */
	@Override
	public void saleCountUpdateC(Map<String, Object> map) throws Exception {
		goodsDAO.saleCountUpdateC(map);
	}
	
	/* 상품 전체 수량 구하기 */
	@Override
	public int allGoodsCount() throws Exception {
		Map<String,Object> mapCount = goodsDAO.allGoodsCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
}