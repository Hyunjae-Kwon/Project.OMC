package omc.member.mypage;

import java.util.List;
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
	
//	//비밀번호 확인
//	@SuppressWarnings("unchecked")
//	public Map<String, Object> checkPw(Map<String, Object> map) throws Exception {
//		return (Map<String, Object>)selectOne("member.findPw",map);
//	}
	
//	//주문내역
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> orderList(Map<String, Object> map){
//		return (List<Map<String, Object>>) selectList("member.orderList",map);
//	}
}
