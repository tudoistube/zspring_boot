/*
 * ...85p.ReaderDto 타입의 컨트롤러 매개변수를 처리하는 인자 리졸버.
 */
package com.example;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ReaderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, 
			  ModelAndViewContainer mavContainer,
			  NativeWebRequest webRequest, 
			  WebDataBinderFactory binderFactory) 
			throws Exception {

		Authentication auth = (Authentication) webRequest.getUserPrincipal();
		
		return auth != null 
		&& 
		auth.getPrincipal() instanceof READER_DTO ? auth.getPrincipal() : null;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		return READER_DTO.class.isAssignableFrom(parameter.getParameterType());
	}

}
