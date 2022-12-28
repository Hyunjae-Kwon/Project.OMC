package omc.common.resolver;

// HandlerMethodArgumentResolver 인터페이스를 상속하면 두가지 메서드를 구현해야 한다.
// supportsParameter 메서드 : Resolver가 적용 가능한지 검사하는 역할 
// resolveArgument 메서드 : 파라미터와 기타 정보를 받아서 실제 객체를 반환
// supportsparameter 메서드는 컨트롤러의 파라미터가 CommandMap 클래스인지 검사하도록 하였다.
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import omc.common.common.CommandMap;

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver{
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return CommandMap.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		CommandMap commandMap = new CommandMap();	// commandMap 객체 생성
		
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		Enumeration<?> enumeration = request.getParameterNames();
		
		String key = null;
		String[] values = null;
		while(enumeration.hasMoreElements()){	// request에 담겨있는 모든 키(key)와 값(value)을 commandMap에 저장
			key = (String) enumeration.nextElement();
			values = request.getParameterValues(key);
			if(values != null){
				commandMap.put(key, (values.length > 1) ? values:values[0] );	// request에 담겨있는 모든 키(key)와 값(value)을 commandMap에 저장
			}
		}
		return commandMap;	// 모든 파라미터가 담겨있는 commandMap을 반환
	}
}