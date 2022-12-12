package omc.common.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="goodsDAO")
	private GoodsDAO goodsDAO;
	
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
	
	/* 상품 상세 정보 */
	@Override
	public Map<String, Object> goodsDetail(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = goodsDAO.goodsDetail(map);
		return resultMap;
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
	
	/* 상품 등록 폼 */
	@Override
	public Map<String, Object> goodsWriteForm(Map<String, Object> map) {
		return goodsDAO.goodsWriteForm(map);
	}
	
	/* 상품 등록 */
	@Override
	public void goodsWrite(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		goodsDAO.goodsWrite(map);
	}
	
	/* 상품 마지막 번호 구하기 */
	@Override
	public int selectGD_GIDMax() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = goodsDAO.selectGD_GIDMax();
		
		int maxGD_GID;
		if(map == null) {
			maxGD_GID = 0;
		} else {
			maxGD_GID = Integer.parseInt(String.valueOf(map.get("GD_GIDMAX")));	
		}
		
		return maxGD_GID;
	}
	
}
