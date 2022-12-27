package omc.common.logger;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("------------------ Interceptor : LoginInterceptor ------------------ ");
		
		String loginId = (String) request.getSession().getAttribute("MEM_ID");
		String uri = request.getRequestURI();
		
		/* 로그인하지 않아도 사용할 수 있는 기능들 */
		// main.al loginForm.al login.al joinForm.al joinSuccess.al 
		// findId.al findIdResult.al findPw.al findPwResult.al confirmId.al
		// allList.al aclList.al etcList.al
		// noticeList.al noticeDetail.al qnaList.al qnaDetail.al

		System.out.println("uri :" + uri);
		if(loginId == null &&
				(
					uri.equals("/main.omc") || uri.equals("/loginForm.omc") || uri.equals("/login.omc") ||
					uri.equals("/joinForm.omc") || uri.equals("/joinSuccess.omc") || uri.equals("/findId.omc") ||
					uri.equals("/findIdResult.omc") || uri.equals("/findPw.omc") || uri.equals("/findPwResult.omc") ||
					uri.equals("/confirmId.omc") || uri.equals("/allGoodsList.omc") || uri.equals("/allGoodsListNew.omc") ||
					uri.equals("/allGoodsListBest.omc") || uri.equals("/noticeList.omc") || uri.equals("/faqList.omc") ||
					uri.equals("/boardList.omc") || uri.equals("/boardDetail1.omc") || uri.equals("/boardDetail.omc") || uri.equals("/allGoodsListCategory.omc")
				)) {
			System.out.println("- 로그인하지 않음 -");
			return true;
		} else if(loginId != null) {
			/* 로그인했을 경우 */

				System.out.println("uri.substring(1, 6) : " + uri.substring(1, 6));
				
				/* 일반 사용자인 경우 관리자 페이지에 접근 불가 */
				if( (!loginId.equals("ADMIN")) && (
						uri.substring(1, 6).equals("admin") || uri.equals("/memberList.omc") || uri.equals("/memberDetail.omc") ||
						uri.equals("/memberModify.omc") || uri.equals("/memberDelete.omc")
						)) {
					System.out.println("- 일반 사용자가 관리자 페이지에 접근 -");
					response.sendRedirect("main.omc");
					return false;					
				}
				/* 관리자일 경우와 일반 사용자가 관리자 페이지가 아닌 페이지에 접근할 경우*/
				else {
					System.out.println("- 로그인 함 올바른 사용 -");
					return true;				
				}				
		} else {
			/* 로그인하지 않았을 때 사용할 수 없는 페이지에 접근할 경우 */
			System.out.println("- 로그인하지않았는데 사용할 수 없는 페이지에 접근 -");
			
			/* 성인인증 메시지 출력 후 주소로 이동 */
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요합니다. 회원가입을 해주세요'); location.href='/loginForm.omc';</script>");
			out.flush();
						 
			return false;
		}
	}
}