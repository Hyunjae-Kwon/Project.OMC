package omc.common.foodtest;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("foodTestDAO")
public class FoodTestDAO extends AbstractDAO{
	
	/* 테스트 결과 가져오기 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> testResultSet(String keyword) throws Exception {
		return (Map<String, Object>) selectOne ("foodtest.testResultSet", keyword);
	}
	
	/* 테스트 결과에 따른 상품 추천 리스트 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> testResultGoods(String keyword) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.testResultGoods", keyword);
	}

}
