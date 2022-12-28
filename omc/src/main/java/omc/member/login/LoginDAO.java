package omc.member.login;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("loginDAO")
public class LoginDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.selectMemberId", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findId(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("member.findId", map);
	}
	
	//비밀번호찾기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findPw(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("member.findPw", map);
	}
	
	/* 상품 상세 정보에서 주문하기로 넘어갈 때 같이 전송하는 로그인 되어있는 회원 정보 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMember(String memberId) throws Exception {
		return (Map<String, Object>) selectOne("member.selectMemberId", memberId);
	}
}