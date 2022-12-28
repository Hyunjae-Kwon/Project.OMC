package omc.common.board;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import omc.common.goods.GoodsDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;

	@Resource(name = "goodsDAO")
	private GoodsDAO goodsDAO;

	//공지리스트
	@Override
	public Map<String, Object> noticeListPagingm(Map<String,Object> map) throws Exception{
		return boardDAO.noticeListPagingm(map);
	}
	
	//faq리스트
	@Override
	public Map<String, Object> faqListPagingm(Map<String,Object> map) throws Exception{
		return boardDAO.faqListPagingm(map);
	}
	
	//커뮤니티 리스트  
	@Override
	public Map<String, Object> boardListPaging(Map<String,Object> map) throws Exception{
		return boardDAO.boardListPaging(map);
	}
	
	//공지,faq,커뮤니티 디테일/조회수
	@Override
	public List<Map<String, Object>> selectBoardId(Map<String,Object>map)  {
		boardDAO.updateHitCnt(map);
		return boardDAO.selectNoticeId(map);
	}

	// 커뮤니티작성

	@Override 
	public void insertBoard(Map<String, Object> map) throws Exception { 
		boardDAO.insertBoard(map);
	}
	//커뮤니티 수정
	@Override
	public Map<String, Object> updateBoardForm(Map<String, Object> map)throws Exception{
		return boardDAO.updateBoardForm(map);
	}

	public void updateBoard(Map<String, Object> map)throws Exception{
		boardDAO.updateBoard(map);
	}

	//커뮤니티글삭제

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		boardDAO.deleteBoard(map);
	}

	/* 마이페이지 리뷰 조회 */
	@Override
	public List<Map<String, Object>> myReviewList(Map<String, Object> map) {
		return boardDAO.myReviewList(map);
	}

	/* 마이페이지 Qna 조회 */
	@Override
	public List<Map<String, Object>> myQnaList(Map<String, Object> map) {
		return boardDAO.myQnaList(map);
	}

	/* 상품 후기 작성 */
	@Override
	public void insertReview(Map<String, Object> map) throws Exception {
		boardDAO.insertReview(map);
	}

	/* 상품 문의 작성 */
	@Override
	public void insertQna(Map<String, Object> map) throws Exception {
		boardDAO.insertQna(map);
	}
}