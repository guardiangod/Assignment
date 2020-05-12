package com.ryan.asmt.service;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryan.asmt.entity.RequestLogging;
import com.ryan.asmt.repository.LoggingRepository;

@Component
public class LoggingServiceImpl implements LoggingService {

	@Autowired
	LoggingRepository loggingRepo;
	
	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		RequestLogging reqLogging = new RequestLogging();
		reqLogging.setTime(new Date());
		reqLogging.setMethod(httpServletRequest.getMethod());
		reqLogging.setApi(httpServletRequest.getRequestURI());
		
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(buildHeadersMap(httpServletRequest)).append("]");
		reqLogging.setHeaders(sb.toString());
		
		sb = new StringBuilder();
		Map<String, String> parameters = buildParametersMap(httpServletRequest);
		if (!parameters.isEmpty()) {
			sb.append("parameters=[").append(parameters).append("] ");
		}
		if (body != null) {
			sb.append("body=[" + body.toString() + "]");
		}
		reqLogging.setParams(sb.toString());
		
		loggingRepo.save(reqLogging);
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RESPONSE [");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] \n");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] \n");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] \n");
        stringBuilder.append("responseBody=[").append(body.toString()).append("] \n");
        stringBuilder.append("]");
		System.out.println(stringBuilder.toString());
	}
	
	private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        
        return resultMap;
    }
	
	private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        return map;
    }
	
	private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        
        return map;
    }

	@Override
	public List<RequestLogging> getAllRequestLogs() {
		List<RequestLogging> reqLoggings = loggingRepo.findAll();
		return reqLoggings;
	}
}
