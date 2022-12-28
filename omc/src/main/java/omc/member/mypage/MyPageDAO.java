package omc.member.mypage;

import java.util.Map;

import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("myPageDAO")
public class MyPageDAO extends AbstractDAO {

	//회원정보가져오기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("member.selectMemberId",map);
	}
	
	//회원정보수정
	public void updateMember(Map<String, Object> map) throws Exception {
		update("member.updateMember", map);
	}
	
	//회원탈퇴
	public void deleteMember(Map<String, Object> map) throws Exception {
		delete("member.deleteMember", map);	
	}
}
