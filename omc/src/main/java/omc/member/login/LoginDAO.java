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
	
	/* 상품 주문하는 회원 정보 (상품 상세 -> 바로 주문) */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMember(String memberId) throws Exception {
		return (Map<String, Object>) selectOne("member.selectMemberId", memberId);
	}
}