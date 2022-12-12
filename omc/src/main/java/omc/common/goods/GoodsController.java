package omc.common.goods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import omc.common.common.CommandMap;
import omc.util.FileUpload;

@Controller
public class GoodsController {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "goodsService")
	GoodsService goodsService;
	
	/* 전체 상품 리스트 */
	@RequestMapping(value="/allGoodsList.omc")
	public ModelAndView allGoodsList(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/goods/allGoodsList");
		
		Map<String,Object> resultMap = goodsService.allGoodsList(commandMap.getMap());
		
		mv.addObject("paginationInfo", (PaginationInfo)resultMap.get("paginationInfo"));
		mv.addObject("allGoodsList", resultMap.get("result"));
		
		return mv;
	}
	
	/* 신상품 리스트 */
	@RequestMapping(value="/allGoodsListNew.omc", method=RequestMethod.GET)
	public ModelAndView newGoodsList(Map<String, Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("goods/newGoodsList");
		
		List<Map<String, Object>> list = goodsService.newGoodsList(commandMap);
		 mv.addObject("newGoodsList", list);
	
		 return mv;
	}
	
	/* 베스트 상품 리스트 */
	@RequestMapping(value="/allGoodsListBest.omc", method=RequestMethod.GET)
	public ModelAndView bestGoodsList(Map<String, Object> commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("goods/bestGoodsList");
		
		List<Map<String, Object>> list = goodsService.bestGoodsList(commandMap);
		System.out.println(list);
		 mv.addObject("bestGoodsList", list);
	
		 return mv;
	}
	
	/* 상품 상세 페이지 */
	@RequestMapping(value = "/goodsDetail.omc") // , method = RequestMethod.POST
	public ModelAndView goodsDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsDetail");
		Map<String, Object> goodsDetail = goodsService.goodsDetail(commandMap.getMap());
		List<Map<String, Object>> goodsReview = goodsService.goodsReview(commandMap.getMap());
		List<Map<String, Object>> goodsQna = goodsService.goodsQna(commandMap.getMap());
		
		HttpSession session = request.getSession();// 세션 값 불러오고 
		String MEM_ID = (String)session.getValue("MEM_ID");// 값을 String 저장하고
		mv.addObject("goods", goodsDetail);
		mv.addObject("reviewList", goodsReview);
		mv.addObject("qnaList", goodsQna);
		session.setAttribute("MEM_ID", MEM_ID);// 세션정보를 MEM_ID 에 담아 jsp로 리턴
		
		return mv;
	}
	
	/* 상품 수정 폼 */
	@RequestMapping(value = "/goodsModifyForm.omc")
	public ModelAndView goodsModifyForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsModifyForm");
	
		return mv;
	}
	
	/* 상품 추가 기능 */
	@RequestMapping(value = "/goodsWrite.omc",  method = RequestMethod.POST)
	public ModelAndView goodsModify(CommandMap commandMap, MultipartHttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView("redirect:/main.omc");
		
		MultipartFile main_imageFile = request.getFile("main_image");
		MultipartFile detail_imageFile1 = request.getFile("image1");
		MultipartFile detail_imageFile2 = request.getFile("image2");
		MultipartFile detail_imageFile3 = request.getFile("image3");
		MultipartFile detail_imageFile4 = request.getFile("image4");
		
		// 마지막에 입력한 상품의 정보로부터 상품번호를 가져온 뒤 (selectPIDMax)
		// 1을 더해서 새로 저장될 파일에 붙여줄 숫자를 구한다.
		int newGD_GID = goodsService.selectGD_GIDMax() + 1;
		
		// 상품번호를 사용해 메인 이미지를 등록
		String uploadMainImageName = "goods-" + newGD_GID +".png";
		
		// 메인 이미지 이름을 product의 GID_IMAGE에 입력
		commandMap.put("GD_IMAGE", uploadMainImageName);
		
		// 상품 이미지를 입력할 폴더 설정
		String path = "/Users/felix/Java/Project.OMC/omc/src/main/webapp/resources/img/goods/";
		//String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "img";
		
		FileUpload.fileUpload(main_imageFile, path, uploadMainImageName);
		
		// 만약 상세 이미지가 존재하면 상세 이미지를 등록
		int detailCount = 0; // 상세 이미지 등록 수, 등록할 때마다 1씩 증가, 등록할 때는 +1로 계산해서 등록 
		if(! detail_imageFile1.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile1, path, uploadDetailImageName);
		} 
		if(! detail_imageFile2.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile2, path, uploadDetailImageName);
		} 
		if(! detail_imageFile3.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile3, path, uploadDetailImageName);
		}
		if(! detail_imageFile4.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile4, path, uploadDetailImageName);
		}
		
		// DB에 상품 정보 입력
		goodsService.goodsWrite(commandMap.getMap(), request);
				
		return mv;
	}
	
	/* 상품 추가 폼 */
	@RequestMapping(value = "/goodsWriteForm.omc")
	public ModelAndView goodsWriteForm(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsWriteForm");
	
		return mv;
	}
	
	/* 상품 추가 기능 */
	@RequestMapping(value = "/goodsWrite.omc",  method = RequestMethod.POST)
	public ModelAndView goodsWrite(CommandMap commandMap, MultipartHttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView("redirect:/main.omc");
		
		MultipartFile main_imageFile = request.getFile("main_image");
		MultipartFile detail_imageFile1 = request.getFile("image1");
		MultipartFile detail_imageFile2 = request.getFile("image2");
		MultipartFile detail_imageFile3 = request.getFile("image3");
		MultipartFile detail_imageFile4 = request.getFile("image4");
		
		// 마지막에 입력한 상품의 정보로부터 상품번호를 가져온 뒤 (selectPIDMax)
		// 1을 더해서 새로 저장될 파일에 붙여줄 숫자를 구한다.
		int newGD_GID = goodsService.selectGD_GIDMax() + 1;
		
		// 상품번호를 사용해 메인 이미지를 등록
		String uploadMainImageName = "goods-" + newGD_GID +".png";
		
		// 메인 이미지 이름을 product의 GID_IMAGE에 입력
		commandMap.put("GD_IMAGE", uploadMainImageName);
		
		// 상품 이미지를 입력할 폴더 설정
		String path = "/Users/felix/Java/Project.OMC/omc/src/main/webapp/resources/img/goods/";
		//String path = request.getSession().getServletContext().getRealPath("/") + File.separator + "img";
		
		FileUpload.fileUpload(main_imageFile, path, uploadMainImageName);
		
		// 만약 상세 이미지가 존재하면 상세 이미지를 등록
		int detailCount = 0; // 상세 이미지 등록 수, 등록할 때마다 1씩 증가, 등록할 때는 +1로 계산해서 등록 
		if(! detail_imageFile1.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile1, path, uploadDetailImageName);
		} 
		if(! detail_imageFile2.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile2, path, uploadDetailImageName);
		} 
		if(! detail_imageFile3.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile3, path, uploadDetailImageName);
		}
		if(! detail_imageFile4.isEmpty()) {
			String uploadDetailImageName = "goods-" + newGD_GID +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile4, path, uploadDetailImageName);
		}
		
		// DB에 상품 정보 입력
		goodsService.goodsWrite(commandMap.getMap(), request);
				
		return mv;
	}
	
//	subList()구독판매페이지
//	saleList()일반판매페이지
//	subDetail()구독판매상세페이지
//	saleDetail()일반판매상세페이지
	// goodsListDetail 리스트 상세

//	// 구독 페이지
//	@RequestMapping(value = "/goods/sub")
//	public ModelAndView subList(CommandMap commandMap) throws Exception {
//		ModelAndView mv = new ModelAndView("subList");	
//		return mv;
//	}
//	
//	@RequestMapping(value ="/goods/goodsSubList")
//	public ModelAndView goodsSubList(CommandMap commandMap) throws Exception{
//		ModelAndView mv = new ModelAndView("jsonView");
//		List<Map<String, Object>> list = goodsService.goodsSubList(commandMap.getMap());
//		mv.addObject("list", list);
//		if(list.size() > 0) {
//			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
//		} else {
//			mv.addObject("TOTAL", 0);
//		}
//		return mv;
//	}
//
//	// 일반 페이지
//	@RequestMapping(value = "/goods/sale")
//	public ModelAndView saleList(CommandMap commandMap) throws Exception {
//		ModelAndView mv = new ModelAndView("saleList");	
//		return mv;
//
//	}
//	@RequestMapping(value ="/goods/goodsSaleList")
//	public ModelAndView goodsSaleList(CommandMap commandMap) throws Exception{
//		ModelAndView mv = new ModelAndView("jsonView");
//		List<Map<String, Object>> list = goodsService.goodsSaleList(commandMap.getMap());
//		mv.addObject("list", list);
//		if(list.size() > 0) {
//			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
//		} else {
//			mv.addObject("TOTAL", 0);
//		}
//		return mv;
//	}
//
//	// 구독 상세 페이지 여기된다 /
//	@RequestMapping(value = "/goods/sub/page")  // , method = RequestMethod.GET
//	public ModelAndView subDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("goodsSubDetail");
//		List<Map<String, Object>> goodsSubDetail = goodsService.getsubDetail(commandMap.getMap());
//		
////		옵션 list  
//		List<String> list11 = new ArrayList<String>();
//		List<String> list12 = new ArrayList<String>();
//		List<String> list13 = new ArrayList<String>();
//	// if .
//		if(goodsSubDetail.get(0).get("GOODS_OP4") != null) {
//			if(goodsSubDetail.get(0).get("GOODS_OP3") != null) {
//				if(goodsSubDetail.get(0).get("GOODS_OP2") != null) {
//					StringTokenizer op4 = new StringTokenizer((String) goodsSubDetail.get(0).get("GOODS_OP4"),  ",");
//					StringTokenizer op3 = new StringTokenizer((String) goodsSubDetail.get(0).get("GOODS_OP3"),  ",");
//					StringTokenizer op2 = new StringTokenizer((String) goodsSubDetail.get(0).get("GOODS_OP2"),  ",");
//				// list 값 담고 
//					while(op4.hasMoreElements()) {
//						list11.add(op4.nextToken());
//					}
//					while(op3.hasMoreElements()) {
//						list12.add(op3.nextToken());
//					}
//					while(op2.hasMoreElements()) {
//						list13.add(op2.nextToken());
//					}
//				}
//			}
//		}
//		
//		List<Map<String, Object>> Review = goodsService.getReview(commandMap.getMap());
//		List<Map<String, Object>> ReviewRe = goodsService.getReviewRe(commandMap.getMap());
//		HttpSession session = request.getSession();// 세션 값 불러오고 
//		String USER_ID = (String)session.getValue("USER_ID");// 값을 String 저장하고
//		mv.addObject("goodsSubDetail", goodsSubDetail);
//		mv.addObject("review", Review);
//		mv.addObject("reviewRe", ReviewRe);
//		mv.addObject("list11", list11);
//		mv.addObject("list12", list12);
//		mv.addObject("list13", list13);
//		session.setAttribute("USER_ID", USER_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
//		return mv;
//	}
//
//	// 일반 상세 페이지
//	@RequestMapping(value = "/goods/sale/page") // , method = RequestMethod.POST
//	public ModelAndView saleDetail(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("goodsSaleDetail");
//		List<Map<String, Object>> goodsSaleDetail = goodsService.getsaleDetail(commandMap.getMap());
////		옵션 list  
//		List<String> list11 = new ArrayList<String>();
//		List<String> list12 = new ArrayList<String>();
//		List<String> list13 = new ArrayList<String>();
//	// if .
//		if(goodsSaleDetail.get(0).get("GOODS_OP4") != null) {
//			if(goodsSaleDetail.get(0).get("GOODS_OP3") != null) {
//				if(goodsSaleDetail.get(0).get("GOODS_OP2") != null) {
//					StringTokenizer op4 = new StringTokenizer((String) goodsSaleDetail.get(0).get("GOODS_OP4"),  ",");
//					StringTokenizer op3 = new StringTokenizer((String) goodsSaleDetail.get(0).get("GOODS_OP3"),  ",");
//					StringTokenizer op2 = new StringTokenizer((String) goodsSaleDetail.get(0).get("GOODS_OP2"),  ",");
//				// list 값 담고 
//					while(op4.hasMoreElements()) {
//						list11.add(op4.nextToken());
//					}
//					while(op3.hasMoreElements()) {
//						list12.add(op3.nextToken());
//					}
//					while(op2.hasMoreElements()) {
//						list13.add(op2.nextToken());
//					}
//				}
//			}
//		}
//		
//		List<Map<String, Object>> Review2 = goodsService.getReview2(commandMap.getMap());
//		List<Map<String, Object>> ReviewRe = goodsService.getReviewRe(commandMap.getMap());
//		HttpSession session = request.getSession();// 세션 값 불러오고 
//		String USER_ID = (String)session.getValue("USER_ID");// 값을 String 저장하고
//		mv.addObject("goodsSaleDetail", goodsSaleDetail);
//		mv.addObject("review2", Review2);
//		mv.addObject("reviewRe", ReviewRe);
//		mv.addObject("list11", list11);
//		mv.addObject("list12", list12);
//		mv.addObject("list13", list13);
//		session.setAttribute("USER_ID", USER_ID);// 세션정보를 user_id 에 담아 jsp로 리턴
//		return mv;
//	}
//	
//	@RequestMapping(value = "/insertgoodsimgPage")
//	public ModelAndView insertgoodsimg(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("insertgoodsimgPage");
//		return mv;
//
//	}
//	@RequestMapping(value = "/insertgoodsthumbPage")
//	public ModelAndView insertgoodsthumb(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView("insertgoodsthumbPage");
//		return mv;
//	}
//	
}
