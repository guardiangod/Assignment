package com.ryan.asmt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryan.asmt.entity.RequestLogging;

public interface LoggingService {

	void logRequest(HttpServletRequest httpServletRequest, Object body);
    
    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
    
    List<RequestLogging> getAllRequestLogs();
}
