package com.ryan.asmt.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ryan.asmt.model.AccessRequest;
import com.ryan.asmt.util.JWTUtil;

import io.jsonwebtoken.Claims;

@Service
@Scope("prototype")
public class AuthorizationService {
	
	@Value("${auth.api.username}")
	private String username;
	
	@Value("${auth.api.password}")
	private String password;
	
	@Value("${auth.api.organization}")
	private String organization;
	
	@Value("${auth.jwt.sign.key}")
	private String signingKey;
	
	@Value("${auth.jwt.lifetime}")
	private String lifeTime;
	
	public boolean IsAuthorizedUser(AccessRequest request) {
		if (StringUtils.equalsIgnoreCase(username, request.getUsername()) &&
			StringUtils.equalsIgnoreCase(password, request.getPassword()) &&
			StringUtils.equalsIgnoreCase(organization, request.getOrganization())) {
			return true;
		}
		
		return false;
	}
	
	public String getAccessToken(AccessRequest request) {
		Map<String, Object> claims = new HashMap<>();
		
		Class<?> clazz = request.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			try {
				claims.put(field.getName().toString(), field.get(request));
			} catch (IllegalArgumentException e1) {
			} catch (IllegalAccessException e2) {}
		}
		
		return JWTUtil.generateJWT(signingKey, Integer.parseInt(lifeTime), claims);
	}
	
	public boolean isAuthorizedAccess(String jwt) {
		boolean isAuthorized = false;
		try {
			Claims claims = JWTUtil.parseJWT(jwt, signingKey);
			if (claims != null) {
				return true;
			}
		} catch (Exception e) {}
		
		return isAuthorized;
	}
}
