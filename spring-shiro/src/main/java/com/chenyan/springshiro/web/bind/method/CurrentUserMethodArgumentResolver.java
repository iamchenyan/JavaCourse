package com.chenyan.springshiro.web.bind.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.chenyan.springshiro.web.bind.annotation.CurrentUser;

/**  
* <p>Title: CurrentUserMethodArgumentResolver</p>  
* @author chenyan  
* @date 2019年8月28日  
*/
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() { } 
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if(parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true ;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class) ;
		return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST) ;
	}

}
