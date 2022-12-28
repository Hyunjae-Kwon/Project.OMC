package omc.member.join;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("joinService")
public class JoinServiceImpl implements JoinService{

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="joinDAO")
	private JoinDAO joinDAO;
	
	// 아이디 중복 검사
	@Override
	public int selectMemberIdCk(String id) throws Exception {
		return joinDAO.selectMemberIdCk(id);
	}

	@Override
	public void insertMember(Map<String, Object> map) throws Exception {
		joinDAO.insertMember(map);
	}
}