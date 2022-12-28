package omc.admin.member;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import omc.common.common.AbstractDAO;

@Repository("adminMemberDAO")
public class AdminMemberDAO extends AbstractDAO {

	Logger log = Logger.getLogger(this.getClass());
	
	// 회원 전체목록 불러오기
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> memberList() throws Exception{
		return (List<Map<String, Object>>) selectList("member.memberList");
	}
	
	//회원 검색, 상세보기
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.selectMemberId", map);
	}
	
	//회원정보 수정
	public void updateMemberAdmin(Map<String, Object> map) throws Exception {
		update("member.updateMemberAdmin", map);
	}
	//회원정보 삭제
	public void deleteMember(Map<String, Object> map) throws Exception {
		delete("member.deleteMember", map);
	}
	
	// 회원 전체목록 페이징
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> memberListPaging(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("member.memberListPaging", map);
	}
	
	// 전체 회원의 수
	@SuppressWarnings("unchecked")
	public Map<String, Object> memberCount() throws Exception {
		return (Map<String, Object>) selectOne("member.memberCount");
	}
	
	// 회원 검색 페이징
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> memberListSearchPaging(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>) selectList("member.memberListSearchPaging", map);
	}
	
	// 검색된 회원의 수
	@SuppressWarnings("unchecked")
	public Map<String, Object> memberSearchCount(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("member.memberSearchCount", map);
	}
}