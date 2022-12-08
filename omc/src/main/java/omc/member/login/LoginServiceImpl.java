package omc.member.login;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Override
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.selectMemberId(map);
	}
	
	//아이디찾기
	@Override
	public List<Map<String, Object>> findId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.findId(map);
	}

	//비밀번호찾기 
	@Override
	public List<Map<String, Object>> findPw(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.findPw(map);
	}

}

