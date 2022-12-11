package omc.member.join;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;


@Controller
public class JoinController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "joinService")
	private JoinService joinService;

//	@Resource(name = "goodsService")
//	GoodsService goodsService;
	
//	/* 메인 세션!!!!!!!!!!!!!!!!!!!!!!!! 여기거된다.*/
//	@RequestMapping("/main.omc")
//	public ModelAndView main(CommandMap commandMap, HttpServletRequest request) throws Exception {
//		HttpSession session = request.getSession();
//		//request의 getSession()메서드의 파라미터로 false를 전달하면, 
//		//이미 생성된 세션이 있을 때 그 세션을 반환하고, 없으면 null을 반환한다.
////		List<Map<String, Object>> list = goodsService.maingoods(commandMap.getMap());
//		String MEM_ID = (String)session.getValue("MEM_ID");
//		ModelAndView mv = new ModelAndView("main");
//		session.setAttribute("MEM_ID", MEM_ID);
////		mv.addObject("list", list);
//		return mv;
//	}

	/* 회원가입 */
	@RequestMapping(value = "/joinForm.omc", method = RequestMethod.GET)
	public ModelAndView joinForm(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("join/joinForm");
		return mv;
	}
	// 회원가입 폼
	@RequestMapping(value = "/joinForm.omc", method = RequestMethod.POST)
    public String registerPOST(String id) throws Exception {
        int idResult = joinService.selectMemberIdCk(id);

        try {
            if (idResult == 1) {
                return "joinForm";
            } else if (idResult == 0) {
                joinService.selectMemberIdCk(id);
                return "redirect:loginForm";
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return "redirect:/";
    }
	
	/* 회원가입완료 */
	@RequestMapping(value = "/joinSuccess.omc", method = RequestMethod.POST)
	public ModelAndView insertMember(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("/main");
	
		joinService.insertMember(commandMap.getMap());
		return mv;
	}

	/* id 중복 체크 */
	@RequestMapping(value = "/confirmId.omc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> confirmId(@RequestBody String MEM_ID) throws Exception {
		Map<String, String> msg = new HashMap<String, String>();
		int count;
		
		
		MEM_ID = MEM_ID.substring(MEM_ID.lastIndexOf("=")+1);
		System.out.println("EMAIL : " + MEM_ID);
		
		/*공백을 입력 받았을 경우*/ 
		if (MEM_ID.trim().equals("")) {
			msg.put("message", "아이디를 입력해주세요");
			return msg;
		} 

		/* 관리자 아이디를 입력 받았을 경우 */
		 if(MEM_ID.trim().equals("ADMIN")) {
			 msg.put("message", "이 아이디는 사용할 수 없습니다.");
			
			 return msg; 
		 }
		
		 Map<String, Object> map = new HashMap<String, Object>();
		 count = joinService.selectMemberIdCk(MEM_ID);		
		
		if(count > 0) { 
			// 중복된 아이디 있음 
			msg.put("message", "이미 가입된 아이디입니다.");
		
		} else {
			//중복된 아이디 없음
			msg.put("message", "사용할 수 있는 아이디입니다.");
		}
		
		return msg;
	
	}
	
}
