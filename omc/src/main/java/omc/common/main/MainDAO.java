package omc.common.main;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("mainDAO")
public class MainDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectGoodsList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList ("goods.selectGoodsList", map);
	}
}