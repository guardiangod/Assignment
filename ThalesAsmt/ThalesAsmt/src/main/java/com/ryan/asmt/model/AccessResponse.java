package com.ryan.asmt.model;

public class AccessResponse extends CommonResponse {

	private static final long serialVersionUID = 1L;
	
	private String jwt;

	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}
