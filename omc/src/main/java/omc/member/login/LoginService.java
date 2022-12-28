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
	
	/* 상품 상세 정보에서 주문하기로 넘어갈 때 같이 전송하는 로그인 되어있는 회원 정보 */
	public Map<String, Object> selectMember(String memberId) throws Exception;	
}