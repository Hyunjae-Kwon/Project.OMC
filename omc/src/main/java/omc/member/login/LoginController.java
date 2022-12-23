package omc.member.login;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import omc.common.common.CommandMap;


@Controller
public class LoginController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "loginService")
	private LoginService loginService;

	//로그인폼
	   @RequestMapping(value = "/loginForm.omc")
	    public String loginForm(HttpServletRequest request) throws Exception {

	        HttpSession session = request.getSession();
	        String getId = (String) session.getValue("MEM_ID");
	        if (getId == null) {
	            return "login/loginForm";
	        }
	        return "main";
	    }
	   
	// 로그인 처리
	   @RequestMapping(value = "/login.omc", method = RequestMethod.POST)
       public ModelAndView login(CommandMap commandMap,HttpServletRequest request) throws Exception {
          ModelAndView mv = new ModelAndView("login/login");
          HttpSession session = request.getSession();
          // session.setAttribute("USER_ID", id); 
          // HttpSession session, @RequestParam String id,

          Map<String,Object> result = loginService.selectMemberId(commandMap.getMap());
          if(result == null || result.get("MEM_ID").equals("N")) { // 아이디가 있는지 or 삭제된 아이디인지 확인
             mv.addObject("msg","회원을 찾을 수 없습니다.");
             mv.addObject("url", "/loginForm.omc");
             return mv;
          } else { 
            
             if(result.get("MEM_PW").equals(commandMap.get("MEM_PW"))) { // 비밀번호가 같다면
                
                if(result.get("MEM_BLOCK").equals("Y")) {
                  mv.addObject("msg","정지된 회원입니다.");
                    mv.addObject("url", "/loginForm.omc");
                    return mv;
                }
                
                session.setAttribute("MEM_ID", commandMap.get("MEM_ID"));
                session.setAttribute("MEM_NUM", result.get("MEM_NUM")); 
                
				// 관리자 체크
				if (result.get("MEM_ID").equals("ADMIN")) {
					mv.addObject("msg", "관리자 로그인 성공!");
					mv.addObject("url", "/adminMain.omc");
					return mv;
				} else {
					mv.addObject("url", "/main.omc");
				}
             } else {//비밀번호가 일치하지 않을 때
                mv.addObject("msg", "비밀번호가 틀립니다.");
                mv.addObject("url", "/loginForm.omc");
                return mv;
      //   session.setAttribute("USER_ID", request.getParameter("${USER_ID}"));            
           }
           
        }   
          mv.addObject("msg", "로그인성공!");
          mv.addObject("url","/main.omc");
          return mv;
    }
	   
	   
		//로그아웃
		@RequestMapping(value="/logout.omc")
		   public void logout(HttpServletRequest request,HttpServletResponse response,CommandMap commandMap) throws Exception {
		      HttpSession session = request.getSession(false);
		      if (session != null)
		         session.invalidate();
		      
		      response.setCharacterEncoding("UTF-8");
			  response.setContentType("text/html; charset=utf-8");
			  PrintWriter out = response.getWriter();
			  out.println("<script>alert('로그아웃됐습니다.'); location.href='"+request.getContextPath()+"/main.omc';</script>");
			 
			  out.flush();
		   }
		
		//아이디찾기
		 @RequestMapping(value = "/findId.omc") // 아이디&비밀번호 찾기 폼을 보여주는 메소드
		   public ModelAndView findId(CommandMap commandMap) throws Exception {
		      ModelAndView mv = new ModelAndView("login/findId");
		      return mv;
		   }
		 
		 @RequestMapping(value = "/findIdResult.omc", method = RequestMethod.POST) // 입력한 정보에 맞춰서 아이디를 찾아주는 거
		   public ModelAndView findIdResult(CommandMap commandMap) throws Exception {
		         ModelAndView mv = new ModelAndView("login/findIdResult");
		         List<Map<String, Object>> list = loginService.findId(commandMap.getMap());
		         mv.addObject("list", list);
		         return mv;
		      }
		 
		 
		//비밀번호찾기 
		 @RequestMapping(value = "/findPw.omc")
			public ModelAndView findPw(CommandMap commandMap) throws Exception {
				ModelAndView mv = new ModelAndView("login/findPw");
			    return mv;
			}
		 
		 @RequestMapping(value = "/findPwResult.omc", method = RequestMethod.POST) // 입력한 정보에 맞춰서 비밀번호를 찾아주는 거
		   public ModelAndView findPwResult(CommandMap commandMap) throws Exception {
		         ModelAndView mv = new ModelAndView("login/findPwResult");
		         List<Map<String, Object>> list = loginService.findPw(commandMap.getMap());
		         mv.addObject("list", list);
		         return mv;
		      }
		 
		 
		
}
