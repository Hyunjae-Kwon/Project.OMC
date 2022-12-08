package omc.member.mypage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MyPageService {

	Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception;
	
	void updateMember(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
	void deleteMember(Map<String, Object> map) throws Exception;
	
//	Map<String, Object> checkPw(Map<String, Object> map) throws Exception;
	
//	List<Map<String, Object>> orderList(Map<String, Object> map);

}
