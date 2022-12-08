package omc.member.mypage;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService {

	@Resource(name="myPageDAO")
	private MyPageDAO myPageDAO;
	
	/* 마이페이지 (회원 정보 가져오기)*/
	@Override
	public Map<String, Object> selectMemberId(Map<String, Object> map) throws Exception {
		
		Map<String, Object> resultMap = myPageDAO.selectMemberId(map);
		
		return resultMap;
	}

	@Override
	public void updateMember(Map<String, Object> map, HttpServletRequest request) throws Exception {
		myPageDAO.updateMember(map);
	}

	@Override
	public void deleteMember(Map<String, Object> map) throws Exception {
		myPageDAO.deleteMember(map);
	}

//	@Override
//	public Map<String, Object> checkPw(Map<String, Object> map) throws Exception {
//		return myPageDAO.checkPw(map);
//	}
	
//	@Override
//	public List<Map<String, Object>> orderList(Map<String, Object> map) {
//		return myPageDAO.orderList(map);
//	}
	
}
