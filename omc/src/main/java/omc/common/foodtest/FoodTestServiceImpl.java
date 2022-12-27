package omc.common.foodtest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("foodTestService")
public class FoodTestServiceImpl implements FoodTestService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="foodTestDAO")
	private FoodTestDAO foodTestDAO;
	
	/* 테스트 결과 가져오기 */
	@Override
	public Map<String, Object> testResultSet(String keyword) throws Exception {
		return foodTestDAO.testResultSet(keyword);
	}

	/* 테스트 결과에 따른 상품 추천 리스트 */
	@Override
	public List<Map<String, Object>> testResultGoods(String keyword) throws Exception {
		return foodTestDAO.testResultGoods(keyword);
	}

}
