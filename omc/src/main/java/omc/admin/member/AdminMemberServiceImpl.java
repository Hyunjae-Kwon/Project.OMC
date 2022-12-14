package omc.admin.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("adminMemberService")
public class AdminMemberServiceImpl implements AdminMemberService {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="adminMemberDAO")
	private AdminMemberDAO adminMemberDAO;

	@Override
	public Map<String, Object> selectMemberId( Map<String, Object> map) throws Exception {
				
		map.put("MEM_ID",map.get("MEM_ID"));
		
		return adminMemberDAO.selectMemberId(map);
	}

	@Override
	public List<Map<String, Object>> memberList() throws Exception {
		return adminMemberDAO.memberList();
	}

	@Override
	public void deleteMember(Map<String, Object> map) throws Exception {
		
		map.put("MEM_ID", map.get("MEM_ID"));
		
		adminMemberDAO.deleteMember(map);
	}

	@Override
	public List<Map<String, Object>> memberListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminMemberDAO.memberListPaging(map);
	}

	@Override
	public int memberCount() throws Exception {
		Map<String, Object> countMap = adminMemberDAO.memberCount();
		
		return Integer.parseInt(String.valueOf(countMap.get("COUNT")));
	}

	@Override
	public List<Map<String, Object>> memberListSearchPaging(String CONDITION, String KEYWORD, int START, int END)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", CONDITION);
		map.put("KEYWORD", KEYWORD);
		map.put("START", START);
		map.put("END", END);
		
		return adminMemberDAO.memberListSearchPaging(map);
	}

	@Override
	public int memberSearchCount(String CONDITION, String KEYWORD) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("CONDITION", CONDITION);
		map.put("KEYWORD", KEYWORD);		
		
		Map<String, Object> countMap = adminMemberDAO.memberSearchCount(map);
		
		return Integer.parseInt(String.valueOf(countMap.get("COUNT")));
	}

	@Override
	public void updateMemberAdmin(Map<String, Object> map) throws Exception {
		adminMemberDAO.updateMemberAdmin(map);
	}
}