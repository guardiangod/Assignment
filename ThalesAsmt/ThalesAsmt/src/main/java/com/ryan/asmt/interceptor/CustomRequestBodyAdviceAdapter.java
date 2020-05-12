package com.ryan.asmt.interceptor;

import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.ryan.asmt.service.LoggingService;

/**
 * Logging requests (POST, PUT, PATCH, DELETE …)
 */
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

	@Autowired
    LoggingService loggingService;
	
	@Autowired
    HttpServletRequest httpServletRequest;
	
	@Override
	public boolean supports(MethodParameter arg0, Type arg1, Class<? extends HttpMessageConverter<?>> arg2) {
		return true;
	}
	
	@Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        loggingService.logRequest(httpServletRequest, body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
