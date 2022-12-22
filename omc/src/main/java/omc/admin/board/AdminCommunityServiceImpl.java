package omc.admin.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import omc.common.common.CommandMap;

@Service("adminCommunityService")
public class AdminCommunityServiceImpl implements AdminCommunityService {

	@Resource(name = "adminCommunityDAO")
	private AdminCommunityDAO adminCommunityDAO;
	
	
	@Override
	public List<Map<String, Object>> noticeList() throws Exception {
		return adminCommunityDAO.noticeList();
	}

	@Override
	public Map<String, Object> selectNoticeId(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BD_NUM", map.get("BD_NUM"));
		
		return adminCommunityDAO.selectNoticeId(map);
	}

	@Override
	public void insertNotice(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BD_TITLE", map.get("BD_TITLE"));
		map.put("BD_ID", map.get("BD_ID"));
		map.put("BD_CONTENT", map.get("BD_CONTENT"));
		
		adminCommunityDAO.insertNotice(map);
	}

	@Override
	public void updateNoticeId(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BD_TITLE", map.get("BD_TITLE"));
		map.put("BD_CONTENT", map.get("BD_CONTENT"));
		map.put("BD_NUM", map.get("BD_NUM"));
		
		adminCommunityDAO.updateNoticeId(map);
	}

	@Override
	public List<Map<String, Object>> reviewList() throws Exception {
		return adminCommunityDAO.reviewList();
		
	}

	@Override
	public List<Map<String, Object>> qnaList() throws Exception {
		return adminCommunityDAO.qnaList();
		
	}

	@Override
	public Map<String, Object> selectQnaId(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BD_NUM", map.get("BD_NUM"));
		
		return adminCommunityDAO.selectQnaId(map);
	}

	@Override
	public List<Map<String, Object>> commentListId(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BC_NUM", map.get("BC_NUM"));
		
		return adminCommunityDAO.commentListId(map);
	}

	@Override
	public void insertComment(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BC_NUM", map.get("BC_NUM"));
		map.put("BC_ID", map.get("BC_ID"));
		map.put("BC_COMMENT", map.get("BC_COMMENT"));
		
		adminCommunityDAO.insertComment(map);
	}

	@Override
	public void updateComment(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BC_COMMENT", map.get("BC_COMMENT"));
		map.put("BC_BCID", map.get("BC_BCID"));
		map.put("BC_NUM", map.get("BC_NUM"));
		map.put("BC_ID", map.get("BC_ID"));
		
		adminCommunityDAO.updateComment(map);
	}

	@Override
	public void deleteComment(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BC_BCID", map.get("BC_BCID"));
		
		adminCommunityDAO.deleteComment(map);
		
	}

	@Override
	public void deleteCommunityId(Map<String, Object> map) throws Exception {
		//Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BD_NUM", map.get("BD_NUM"));
		
		adminCommunityDAO.deleteCommunityId(map);
	}
	
	/* 페이징 */
	@Override
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminCommunityDAO.noticeListPaging(map);
	}

	@Override
	public List<Map<String, Object>> reviewListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminCommunityDAO.reviewListPaging(map);
	}

	@Override
	public List<Map<String, Object>> qnaListPaging(int START, int END) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("START", START);
		map.put("END", END);
		
		return adminCommunityDAO.qnaListPaging(map);		
	}
	
	@Override
	public int noticeListCount() throws Exception {
		Map<String,Object> mapCount = adminCommunityDAO.noticeListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
	
	@Override
	public int qnaListCount() throws Exception {
		Map<String,Object> mapCount = adminCommunityDAO.qnaListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
	
	@Override
	public int reviewListCount() throws Exception {
		Map<String,Object> mapCount = adminCommunityDAO.reviewListCount();
		return Integer.parseInt(String.valueOf(mapCount.get("COUNT")));
	}
}