package omc.admin.member;

import java.util.List;
import java.util.Map;

public interface AdminMemberService {

	List<Map<String, Object>> memberList() throws Exception;

	List<Map<String, Object>> memberListPaging(int sTART, int eND) throws Exception;

	List<Map<String, Object>> memberListSearchPaging(String condition, String keyword, int sTART, int eND) throws Exception;

	int memberSearchCount(String condition, String keyword) throws Exception;

	int memberCount() throws Exception;

	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception;
	
	public void deleteMember(Map<String, Object> map) throws Exception;

	void updateMemberAdmin(Map<String, Object> map) throws Exception;
}