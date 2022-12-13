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

	/* 상품 상세 정보에서 주문하기로 넘어갈 때 같이 전송하는 로그인 되어있는 회원 정보 */
	@Override
	public Map<String, Object> selectMember(String memberId) throws Exception {
		return loginDAO.selectMember(memberId);
	}

}

