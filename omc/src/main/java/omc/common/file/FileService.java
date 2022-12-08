package omc.common.file;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FileService {
    
    public void insertGoodsThumb(Map<String, Object> map, HttpServletRequest request) throws Exception;
//    public void insertGoodsImage(Map<String, Object> map, HttpServletRequest request) throws Exception;
    public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;

}