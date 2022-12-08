package omc.member.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("member.selectMemberId", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findId(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("member.findId", map);
	}
	
	//비밀번호찾기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findPw(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) selectList("member.findPw", map);
	}
}
