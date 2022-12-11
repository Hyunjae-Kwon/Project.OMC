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
	
	/* 상품 추가 */
	@Override
	public Map<String, Object> goodsWriteForm(Map<String, Object> map) {
		return goodsDAO.goodsWriteForm(map);
	}

	@Override
	public void goodsWrite(Map<String, Object> map, HttpServletRequest request) throws Exception {
		goodsDAO.goodsWrite(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++){
			goodsDAO.goodsWriteImage(list.get(i));
		}
		
	}

}
