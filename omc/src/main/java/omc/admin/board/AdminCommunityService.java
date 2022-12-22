package omc.admin.board;

import java.util.List;
import java.util.Map;

import omc.common.common.CommandMap;

public interface AdminCommunityService {

	public List<Map<String, Object>> noticeList()throws Exception;
	
	public Map<String, Object> selectNoticeId(Map<String, Object> map)throws Exception;
	
	public void insertNotice(Map<String, Object> map)throws Exception;
		
	public void updateNoticeId(Map<String, Object> map)throws Exception;
	
	public List<Map<String, Object>> reviewList()throws Exception;
	
	public List<Map<String, Object>> qnaList()throws Exception;
	
	public Map<String, Object> selectQnaId(Map<String, Object> map)throws Exception;
	
	public List<Map<String, Object>> commentListId(Map<String, Object> map)throws Exception;
	
	public void insertComment(Map<String, Object> map)throws Exception;
	
	public void updateComment(Map<String, Object> map)throws Exception;
	
	public void deleteComment(Map<String, Object> map)throws Exception;
	
	public void deleteCommunityId(Map<String, Object> map)throws Exception;
	
	/* 페이징 */
	public List<Map<String, Object>> noticeListPaging(int START, int END) throws Exception;
	
	public List<Map<String, Object>> reviewListPaging(int START, int END)throws Exception;
	
	public List<Map<String, Object>> qnaListPaging(int START, int END)throws Exception;
	
	public int noticeListCount() throws Exception;
	
	public int qnaListCount() throws Exception;
	
	public int reviewListCount() throws Exception;

}
