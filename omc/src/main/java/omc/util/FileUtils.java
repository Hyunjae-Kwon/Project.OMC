package omc.util;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	public Map<String,Object> parseInsertFileInfo(Map<String,Object> map, MultipartHttpServletRequest request) throws Exception{
		MultipartFile main_imageFile = request.getFile("main_image");
		MultipartFile detail_imageFile1 = request.getFile("image1");
		MultipartFile detail_imageFile2 = request.getFile("image2");
		MultipartFile detail_imageFile3 = request.getFile("image3");
		MultipartFile detail_imageFile4 = request.getFile("image4");
		
		//상품번호를 불러옴
		String boardIdx = (String) map.get("GD_GID");
		
		// 상품번호를 사용해 메인 이미지를 등록
		String uploadMainImageName = "goods-" + boardIdx +".png";
		
		// 메인 이미지 이름을 product의 GID_IMAGE에 입력
		map.put("GD_IMAGE", uploadMainImageName);
		
		// 상품 이미지를 입력할 폴더 설정
		String path = "C:\\java\\stsApp\\omc\\src\\main\\webapp\\resources\\img\\goods\\";
				
		FileUpload.fileUpload(main_imageFile, path, uploadMainImageName);
		
		// 만약 상세 이미지가 존재하면 상세 이미지를 등록
		int detailCount = 0; // 상세 이미지 등록 수, 등록할 때마다 1씩 증가, 등록할 때는 +1로 계산해서 등록 
		if(! detail_imageFile1.isEmpty()) {
			String uploadDetailImageName = "goods-" + boardIdx +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile1, path, uploadDetailImageName);
		} 
		if(! detail_imageFile2.isEmpty()) {
			String uploadDetailImageName = "goods-" + boardIdx +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile2, path, uploadDetailImageName);
		} 
		if(! detail_imageFile3.isEmpty()) {
			String uploadDetailImageName = "goods-" + boardIdx +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile3, path, uploadDetailImageName);
		}
		if(! detail_imageFile4.isEmpty()) {
			String uploadDetailImageName = "goods-" + boardIdx +"-detail" + (detailCount+1) + ".png";
			detailCount++;
			FileUpload.fileUpload(detail_imageFile4, path, uploadDetailImageName);
		}
		
		return map;
	}
}