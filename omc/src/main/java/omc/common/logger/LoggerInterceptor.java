package omc.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import omc.common.logger.LoggerInterceptor;

public class LoggerInterceptor extends HandlerInterceptorAdapter {	// HandlerInterceptorAdapter 클래스를 상속받음
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);	// Log4j의 Log 객체를 log라는 이름으로 생성
	
	// preHandle() : client -> controller 로 요청할 때, 그 요청을 처리할 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================          START         ======================================");
			log.debug(" Request URI \t:  " + request.getRequestURI());	// 현재 호출된 URI가 무엇인지 보여줌.
		}
		return super.preHandle(request, response, handler);
	}
	
	// postHandle() : controller -> client 로 응답할 때, 그 요청을 처리할 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================           END          ======================================\n");
		}
	}
}
