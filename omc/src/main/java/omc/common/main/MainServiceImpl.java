package omc.common.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("mainService")
public class MainServiceImpl implements MainService {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="mainDAO")
	private MainDAO mainDAO;
	
	@Override
	public List<Map<String, Object>> selectGoodsList(Map<String, Object> map) throws Exception {
		return mainDAO.selectGoodsList(map);
	}

}
