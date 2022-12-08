package omc.member.login;

import java.util.List;
import java.util.Map;

public interface LoginService {
	//로그인
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception;
	
	//아이디찾기
	public List<Map<String, Object>> findId(Map<String, Object> map) throws Exception;
	
	//비밀번호 찾기
	public List<Map<String, Object>> findPw(Map<String, Object> map)throws Exception;
	
}
