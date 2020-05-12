package com.ryan.asmt.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.asmt.entity.RequestLogging;
import com.ryan.asmt.model.AccessRequest;
import com.ryan.asmt.model.AccessResponse;
import com.ryan.asmt.model.AdditionRequest;
import com.ryan.asmt.model.AdditionResponse;
import com.ryan.asmt.model.CommonResponse;
import com.ryan.asmt.service.AuthorizationService;
import com.ryan.asmt.service.LoggingService;
import com.ryan.asmt.util.Constants;

@RestController
@RequestMapping("/api/v1")
public class MainController {

	@Autowired
    LoggingService loggingService;
	
	@Autowired
	AuthorizationService authService;
	
	@PostMapping("/addition")
	public AdditionResponse doAddition(@Valid @RequestBody AdditionRequest request) {
		int sum = Integer.sum(request.getNum1(), request.getNum2());
		
		AdditionResponse response = new AdditionResponse();
		response.setResult(sum);
		response.setMessage(Constants.SUCCESS_MESSAGE);
		
		return response;
	}
	
	@GetMapping("/logging")
    public ResponseEntity<Object> getLoggings(@RequestHeader HttpHeaders headers) {
		if (headers != null && headers.containsKey(HttpHeaders.AUTHORIZATION)) {
			String bearer = headers.get(HttpHeaders.AUTHORIZATION).get(0).toString();
			
			if (StringUtils.isNotBlank(bearer) && StringUtils.containsIgnoreCase(bearer, Constants.BEARER_PREFIX)) {
				String jwt = StringUtils.removeStartIgnoreCase(bearer, Constants.BEARER_PREFIX).trim();
				
				if (authService.isAuthorizedAccess(jwt)) {
					List<RequestLogging> reqLoggings = loggingService.getAllRequestLogs();
					return new ResponseEntity<Object>(reqLoggings, HttpStatus.OK);
				}
				
				CommonResponse errorResponse  = new CommonResponse(Constants.AUTHENTICATION_FAILED, Constants.INVALID_ACCESS_TOKEN);
				return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
			}
			
			CommonResponse errorResponse  = new CommonResponse(Constants.AUTHENTICATION_ERROR, Constants.MISSING_ACCESS_TOKEN);
			return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		
		CommonResponse errorResponse  = new CommonResponse(Constants.AUTHENTICATION_ERROR, Constants.MISSING_HEADERS);
		return new ResponseEntity<Object>(errorResponse, HttpStatus.FORBIDDEN);
    }
	
	@GetMapping("/realms")
	public ResponseEntity<Object> verifyUserAccess(@Valid @RequestBody AccessRequest request) {
		if (authService.IsAuthorizedUser(request)) {
			String jwt = authService.getAccessToken(request);
			if (StringUtils.isNotEmpty(jwt)) {
				AccessResponse response = new AccessResponse();
				response.setJwt(jwt);
				response.setMessage(Constants.SUCCESS_MESSAGE);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
		}
		
		CommonResponse failResponse  = new CommonResponse(Constants.UNAUTHENTICATED, Constants.INVALID_CREDENTIALS);
		return new ResponseEntity<Object>(failResponse, HttpStatus.UNAUTHORIZED);
	}
}
