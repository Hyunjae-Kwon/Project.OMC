package omc.common.file;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;

@Controller
public class FileUploadController {

    private static final Logger log = Logger.getLogger(FileUploadController.class);
    
    @Autowired
    FileService fileService;
    
	/*
	 * @Resource(name = "goodsService") GoodsService goodsService;
	 */
	/*
	 * @RequestMapping(value = "/goodsWritePage", method = RequestMethod.GET) public
	 * ModelAndView getFilePage(CommandMap inparams) { ModelAndView mav = new
	 * ModelAndView("goodsWritePage"); return mav; }
	 */
    
    @RequestMapping(value = "/upload/insertGoodsThumb.omc", method = RequestMethod.POST)
    public ModelAndView insertGoodsThumbs(CommandMap inparams, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");            
        if (log.isDebugEnabled()) {
            log.debug(inparams);
        }
        
        fileService.insertGoodsThumb(inparams.getMap(), request);
        
        
        
        return mav;
    }
   
//    @RequestMapping(value = "/upload/insertGoodsImage.omc", method = RequestMethod.POST)
//    public ModelAndView insertGoodsImage(CommandMap inparams, HttpServletRequest request) throws Exception {
//        ModelAndView mav = new ModelAndView("jsonView");
//        if (log.isDebugEnabled()) {
//            log.debug(inparams);
//        }
//        
//        fileService.insertGoodsImage(inparams.getMap(), request);
//        return mav;
//    }
    
    @RequestMapping(value = "/upload/insertBoard.omc", method = RequestMethod.POST)
    public ModelAndView insertBoard(CommandMap inparams, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        if (log.isDebugEnabled()) {
            log.debug(inparams);
        }
        
        fileService.insertBoard(inparams.getMap(), request);
        return mav;
    }
    
}