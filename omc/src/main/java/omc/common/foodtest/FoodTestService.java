package omc.common.foodtest;

import java.util.List;
import java.util.Map;

public interface FoodTestService {
	
	/* 테스트 결과 가져오기 */
	Map<String, Object> testResultSet(String keyword) throws Exception;
	
	/* 테스트 결과에 따른 상품 추천 리스트 */
	List<Map<String, Object>> testResultGoods(String keyword) throws Exception;

}
