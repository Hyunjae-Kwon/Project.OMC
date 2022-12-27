package omc.admin.goods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.common.CommandMap;
import omc.common.goods.GoodsService;
import omc.util.FileUpload;
import omc.util.Paging;

@Controller
public class AdminGoodsController {

	@Resource(name="adminGoodsService")
	private AdminGoodsService adminGoodsService;
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	/* 관리자 상품 리스트 */
	@RequestMapping(value="/adminGoodsList.omc")
	public ModelAndView adminPList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("admin/goods/adminGoodsList");
		/* 페이징을 위한 변수 */
		int pageSize = 10; // 페이지당 출력할 상품의 수
		int START = 1;
		int END = pageSize;
		int currentPage = 1; // 현재 페이지

		int countProductAll; // 전체 상품의 수
		int pageBlock = 5; // 표시할 페이지의 수
		String url = "adminGoodsList.omc";
		String searchUrl = "";
		
		/* 검색 조건일 경우 사용 변수 */
		// -1, null일 경우 검색 조건에서 빠진다.
		String KEYWORD = request.getParameter("keyword"); // 검색 키워드 문자일때
		int KEYNUMBER = -1;
		
		/* 기본 페이지가 아닐 경우 */
		if(request.getParameter("page")!=null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
			START = 1 + pageSize*(currentPage-1); 
			END = pageSize*currentPage;
		}
		
		/* KEYWORD값이 null이 아닌 ""일 때 null로 설정 */
		if(KEYWORD!=null && KEYWORD.equals("")) {
			KEYWORD = null;
		}
		
		/* KEYWORD를 입력받았을 때 숫자로 이루어져있는지 검사 */
		boolean isNumber = false; // isNumber가 false이면 문자 포함, true이면 수
		if(KEYWORD!=null) {
			int i = 0;
			while(i<KEYWORD.length()) {
				if(!Character.isDigit(KEYWORD.charAt(i))) {
					break;
				}
				i++;
			}
			System.out.println("i    : " + i);
			// 모든 검사 루프를 통과하면 수
			if(i==KEYWORD.length()) {
				isNumber = true;
			}
		}
		/* 만약 KEYWORD가 숫자로 이루어져있으면 KEYNUMBER값에 KEYWORD를 숫자로 변환한 값을 입력 */
		if(isNumber == true) {
			KEYNUMBER = Integer.parseInt(KEYWORD);				
		}
		
		if(KEYWORD == null) {
			countProductAll = goodsService.allGoodsCount();
		} else {
			countProductAll = adminGoodsService.allListKeywordCount(KEYWORD, KEYNUMBER);
		}
		
		// 페이징할 아이템의 총 수, 페이지의 수 ex> 1~5 6~10, 한 페이지에 표시할 아이템의 수, 현재 페이지, 이동주소, 검색시 사용할 주소 입력
		Paging paging = new Paging(countProductAll,	pageBlock,
			pageSize ,currentPage, url, searchUrl);
		
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> adminGoodsList = new ArrayList<Map<String, Object>>();
		
		// 검색 조건에 따라(키워드가 null이 아니면) allListKeywordPaging으로 list설정
		if(KEYWORD == null) {
			list = adminGoodsService.allListPaging(START, END);
		} else {
			list = adminGoodsService.allListKeywordPaging(KEYWORD, KEYNUMBER, START, END);
		}
		for(Map<String, Object> mapObject : list) {
			adminGoodsList.add((mapObject));
		}
		
		mv.addObject("adminGoodsList", adminGoodsList);
		
		/* 페이징을 위한 값 삽입 */
		mv.addObject("currentPage", currentPage);
		mv.addObject("paging", paging);
		
		return mv;
		
	}
	
	/* 상품 등록 폼 */
	@RequestMapping(value = "/adminGoodsWriteForm.omc")
	public ModelAndView adminGoodsWriteForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/goods/adminGoodsWriteForm");
	
		return mv;
	}
	
	/* 상품 등록 기능 */
	@RequestMapping(value = "/adminGoodsWrite.omc",  method = RequestMethod.POST)
	public ModelAndView adminGoodsWrite(CommandMap commandMap, MultipartHttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView("redirect:/adminGoodsList.omc");
		
		// DB에 상품 정보 입력
		adminGoodsService.adminGoodsWrite(commandMap.getMap(), request);
		
		mv.addObject("msg", "상품을 등록하였습니다.");
		mv.addObject("url", "/adminGoodsList.omc");
				
		return mv;
	}
	
	/* 상품 수정 폼 */
	@RequestMapping(value = "/adminGoodsModifyForm.omc")
	public ModelAndView adminGoodsModifyForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/goods/adminGoodsModifyForm");
		
		Map<String,Object> goods = goodsService.goodsDetail(commandMap.getMap());
		mv.addObject("goods", goods);
	
		return mv;
	}
	
	/* 상품 수정 기능 */
	@RequestMapping(value = "/adminGoodsModify.omc",  method = RequestMethod.POST)
	public ModelAndView adminGoodsModify(CommandMap commandMap, MultipartHttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView("admin/goods/adminGoodsModify");
		
		// 상품 이미지를 입력할 폴더 설정
		String path = "/Users/felix/Java/Project.OMC/omc/src/main/webapp/resources/img/goods/";
		
		// 상품 수정이기 때문에 GD_GID 값은 폼에서 입력받음
		String newGD_GID = commandMap.get("GD_GID").toString();
				
		// 상품번호를 사용해 메인 이미지를 등록
		String uploadMainImageName = "goods-" + newGD_GID +".png";
				
		// 메인 이미지 이름을 goods의 GD_IMAGE에 입력
		commandMap.put("GD_IMAGE", uploadMainImageName);
		
		MultipartFile main_imageFile = request.getFile("main_image");
		MultipartFile detail_imageFile1 = request.getFile("image1");
		MultipartFile detail_imageFile2 = request.getFile("image2");
		MultipartFile detail_imageFile3 = request.getFile("image3");
		MultipartFile detail_imageFile4 = request.getFile("image4");
		
		// 만약 수정할 이미지 파일을 입력했으면 기존 파일을 삭제한 뒤 삽입
		if(! main_imageFile.isEmpty()) {
			String delMainPath = path + uploadMainImageName;
			File delMainFile = new File(delMainPath);	
			delMainFile.delete();
			FileUpload.fileUpload(main_imageFile, path, uploadMainImageName);
		}
		
		// 만약 수정할 상세 이미지를 업로드했으면 기존 상세 이미지르 삭제하고 새로 등록
		if(! detail_imageFile1.isEmpty()) {
			String delPath = path + "/" + "goods-" + newGD_GID + "-detail1.png";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "goods-" + newGD_GID + "-detail1.png";
			FileUpload.fileUpload(detail_imageFile1, path, uploadDetailImageName);
		} 
		if(! detail_imageFile2.isEmpty()) {
			String delPath = path + "/" + "goods-" + newGD_GID + "-detail2.png";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "goods-" + newGD_GID + "-detail2.png";
			FileUpload.fileUpload(detail_imageFile2, path, uploadDetailImageName);
		} 
		if(! detail_imageFile3.isEmpty()) {
			String delPath = path + "/" + "goods-" + newGD_GID + "-detail3.png";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "goods-" + newGD_GID + "-detail3.png";
			FileUpload.fileUpload(detail_imageFile3, path, uploadDetailImageName);
		}
		if(! detail_imageFile4.isEmpty()) {
			String delPath = path + "/" + "goods-" + newGD_GID + "-detail4.png";
			File delFile = new File(delPath);
			delFile.delete();
			String uploadDetailImageName = "goods-" + newGD_GID + "-detail4.png";
			FileUpload.fileUpload(detail_imageFile4, path, uploadDetailImageName);
		}
		
		// DB에 상품 정보 입력
		adminGoodsService.adminGoodsModify(commandMap.getMap(), request);
		
		mv.addObject("msg", "상품을 수정하였습니다.");
		mv.addObject("url", "/adminGoodsList.omc");
		
		return mv;
	}
	
	/* 상품 삭제 */
	@RequestMapping(value="/adminGoodsDelete.omc")
	public ModelAndView adminGoodsDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("admin/goods/adminGoodsDelete");
		
		String GD_GID = (String)commandMap.get("GD_GID");
		
		String path = "/Users/felix/Java/Project.OMC/omc/src/main/webapp/resources/img/goods/";
		File file = new File(path + "goods-" + GD_GID + ".png");
		if(file.exists()) {
			file.delete();
		}
		
		File file1 = new File(path + "goods-" + GD_GID + "-detail1" + ".png");
		if(file1.exists()) {
			file1.delete();
		}
		
		File file2 = new File(path + "goods-" + GD_GID + "-detail2" + ".png");
		if(file2.exists()) {
			file2.delete();
		}
		
		File file3 = new File(path + "goods-" + GD_GID + "-detail3" + ".png");
		if(file3.exists()) {
			file3.delete();
		}
		
		File file4 = new File(path + "goods-" + GD_GID + "-detail4" + ".png");
		if(file4.exists()) {
			file4.delete();
		}
		
		// DB에 상품 정보 삭제
		adminGoodsService.adminGoodsDelete(commandMap.getMap(), request);
		
		adminGoodsService.deleteCartGID(commandMap.getMap());		
		
		mv.addObject("msg", "상품을 삭제하였습니다.");
		mv.addObject("url", "/adminGoodsList.omc");
		
		return mv;
	}
	
	/* 매출 리스트 */
//	@RequestMapping(value="/adminSellList.omc")
//	public ModelAndView adminSellList(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		// mybatis allListSearchPaging에서 PSELL값을 이용하여 내림차순 정렬 사용
//		ModelAndView mv = new ModelAndView("admin/goods/adminSellList");
//		
//		/* 페이징을 위한 변수 */
//		int pageSize = 10; // 페이지당 출력할 상품의 수
//		int START = 1;
//		int END = pageSize;
//		int currentPage = 1; // 현재 페이지
//
//		int countGoodsAll; // 전체 상품의 수
//		int pageBlock = 5; // 표시할 페이지의 수
//		String url = "adminSellList.omc";
//		String searchUrl = "";
//		
//		/* 기본 페이지가 아닐 경우 */
//		if(request.getParameter("page")!=null) {
//			currentPage = Integer.parseInt(request.getParameter("page"));
//			START = 1 + pageSize*(currentPage-1); 
//			END = pageSize*currentPage;
//		}
//		
//		countGoodsAll = goodsService.allGoodsCount();
//		
//		List<Map<String, Object>> list = adminGoodsService.selectGoodsListPaging(START, END);
//		List<Map<String, Object>> sellList = new ArrayList<Map<String, Object>>();
//		for(Map<String, Object> mapObject : list) {
//			sellList.add(mapObject);
//		}
//		
//		mv.addObject("sellList", sellList);
//		
//		Paging paging = new Paging(countGoodsAll, pageBlock,
//				pageSize ,currentPage, url, searchUrl);
//
//		/* 페이징을 위한 값 삽입 */
//		mv.addObject("currentPage", currentPage);
//		mv.addObject("paging", paging);
//		
//		return mv;
//	}	
	
	/* 매출 리스트 */
	@RequestMapping(value="/adminSellList.omc")
	public ModelAndView allGoodsList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("admin/goods/adminSellList");
		
		Map<String,Object> resultMap = adminGoodsService.selectGoodsListPaging(commandMap.getMap());
		Map<String,Object> sum = adminGoodsService.sellSum(commandMap.getMap());
		String sort = (String)commandMap.get("sort");		
		
		mv.addObject("sum", sum.get("SUM"));
		mv.addObject("sort", sort);
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("sellList", resultMap.get("result"));
		
		return mv;
	}

}