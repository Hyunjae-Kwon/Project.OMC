package omc.common.main;

import java.util.List;
import java.util.Map;

public interface MainService {
	
	List<Map<String, Object>> selectGoodsList(Map<String, Object> map) throws Exception;
}