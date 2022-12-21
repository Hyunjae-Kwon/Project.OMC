package omc.common.goods;

import java.io.File;
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
	
	/* 카테고리 상품 리스트 */
	@RequestMapping(value="/allGoodsListCategory.omc", method=RequestMethod.GET)
	public ModelAndView categoryGoodsList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("goods/categoryGoodsList");
		
		String GD_CATEGORY = (String)commandMap.get("GD_CATEGORY");
	
		List<Map<String, Object>> list = goodsService.categoryGoodsList(commandMap.getMap());
		mv.addObject("category", GD_CATEGORY);
		mv.addObject("categoryGoodsList", list);
		
		return mv;
	}
	
	/* 상품 검색 */
//	@RequestMapping(value="/searchGoodsList.omc", method=RequestMethod.GET)
//	public ModelAndView searchGoodsList(CommandMap commandMap, HttpServletRequest request) throws Exception{
//		ModelAndView mv = new ModelAndView("goods/searchGoodsList");
//		
//		List<Map<String, Object>> list = goodsService.searchGoodsList(commandMap.getMap());
//		mv.addObject("goods", list);
//		
//		return mv;
//	}
	
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
		
		Map<String,Object> map = goodsService.goodsDetail(commandMap.getMap());
		mv.addObject("goods", map);
	
		return mv;
	}
	
	/* 상품 수정 기능 */
	@RequestMapping(value = "/goodsModify.omc",  method = RequestMethod.POST)
	public ModelAndView goodsModify(CommandMap commandMap, MultipartHttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView("redirect:/main.omc");
		
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
		goodsService.goodsModify(commandMap.getMap(), request);
		
		return mv;
	}
	
	/* 상품 삭제 */
	@RequestMapping(value="/goodsDelete.omc")
	public ModelAndView goodsDelete(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("goods/goodsDelete");
		
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
		goodsService.goodsDelete(commandMap.getMap(), request);
		
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
		
		// DB에 상품 정보 입력
		goodsService.goodsWrite(commandMap.getMap(), request);
				
		return mv;
	}
}
