package com.ryan.asmt.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ryan.asmt.service.LoggingService;

@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

	@Autowired
    LoggingService loggingService;
	
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter parameter, MediaType mediaType,
			Class<? extends HttpMessageConverter<?>> converterType,
			ServerHttpRequest servletRequest, ServerHttpResponse servletResponse) {
		if (servletRequest instanceof ServletServerHttpRequest && servletResponse instanceof ServletServerHttpResponse) {
            loggingService.logResponse(
            		((ServletServerHttpRequest) servletRequest).getServletRequest(),
                    ((ServletServerHttpResponse) servletResponse).getServletResponse(), 
                    body);
        }
        
        return body;
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		return true;
	}

}
