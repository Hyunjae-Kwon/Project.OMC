package omc.admin.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import omc.util.FileUtils;

@Service("adminGoodsService")
public class AdminGoodsServiceImpl implements AdminGoodsService {

	@Resource(name="adminGoodsDAO")
	private AdminGoodsDAO adminGoodsDAO; 
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Override
	public List<Map<String, Object>> allListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminGoodsDAO.allListPaging(map);
	}
	
	@Override
	public List<Map<String, Object>> goodsList() throws Exception {
		return adminGoodsDAO.goodsList();
	}
	
	@Override
	public List<Map<String, Object>> allListKeywordPaging(String KEYWORD, int KEYNUMBER, int START, int END)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("KEYWORD", KEYWORD);
		if(KEYNUMBER == -1) {
			map.put("KEYNUMBER", null);
		} else {
			map.put("KEYNUMBER", KEYNUMBER);			
		}
		map.put("START", START);
		map.put("END", END);
		
		
		System.out.println("---- allListKeywordPaging ----");
		System.out.println("");
		System.out.println("");
		System.out.println("---- allListKeywordPaging ----");
		
		return adminGoodsDAO.allListKeywordPaging(map);
	}

	@Override
	public int allListKeywordCount(String KEYWORD, int KEYNUMBER) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("KEYWORD", KEYWORD);
		if(KEYNUMBER == -1) {
			map.put("KEYNUMBER", null);
		} else {
			map.put("KEYNUMBER", KEYNUMBER);			
		}
		Map<String, Object> countMap = adminGoodsDAO.allListKeywordCount(map);
		
		int keywordCount = Integer.parseInt(String.valueOf(countMap.get("COUNT")));
		return keywordCount;
	}

	@Override
	public void deleteCartGID(Map<String, Object> map) throws Exception {
		 
		map.put("CT_GID", map.get("GD_GID"));
		
		adminGoodsDAO.deleteCartGID(map);
	}

	@Override
	public int selectGIDMax() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
		
		map = adminGoodsDAO.selectGIDMax();
		
		int maxGID;
		if(map == null) {
			maxGID = 0;
		} else {
			maxGID = Integer.parseInt(String.valueOf(map.get("GD_GIDMAX")));	
		}
		
		return maxGID;
	}

	@Override
	public Map<String, Object> insertGoods(Map<String, Object> map, MultipartHttpServletRequest request)
			throws Exception {
		
		return adminGoodsDAO.insertGoods(map);
	}

	/* 상품 등록 */
	@Override
	public void adminGoodsWrite(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		adminGoodsDAO.adminGoodsWrite(map);
		
		Map<String,Object> map1 = fileUtils.parseInsertFileInfo(map, request);
		adminGoodsDAO.adminUpdateImg(map1);
	}
	
	/* 상품 수정 폼 */
//	@Override
//	public Map<String, Object> goodsModifyForm(Map<String, Object> map) {
//		return goodsDAO.goodsModifyForm(map);
//	}
	
	/* 상품 수정 */
	@Override
	public void adminGoodsModify(Map<String, Object> map, MultipartHttpServletRequest request) throws Exception {
		adminGoodsDAO.adminGoodsModify(map);
	}
	
	/* 상품 삭제 */
	@Override
	public void adminGoodsDelete(Map<String, Object> map, HttpServletRequest request) throws Exception {
		adminGoodsDAO.adminGoodsDelete(map);
	}
	
	/* 전체 상품 리스트 */
//	@Override
//	public List<Map<String, Object>> selectGoodsListPaging(int START, int END) throws Exception {
//        Map<String,Object> map = new HashMap<String,Object>();
//		
//		 map.put("START", START);
//		 map.put("END", END);
//		
//		return adminGoodsDAO.selectGoodsListPaging(map);
//	}
	
	/* 전체 상품 리스트, 매출 리스트 */
    @Override
	public Map<String, Object> selectGoodsListPaging(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return adminGoodsDAO.selectGoodsListPaging(map);
	}

    /* 매출 총합 */
	@Override
	public Map<String, Object> sellSum(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return adminGoodsDAO.sellSum(map);
	}
	
}