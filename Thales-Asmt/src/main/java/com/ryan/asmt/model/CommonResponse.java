package com.ryan.asmt.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CommonResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	
	private String message;
	
	@JsonInclude(Include.NON_EMPTY)
	private String details;
	
	public CommonResponse() {
		super();
		this.timestamp = new Date();
	}
	
	public CommonResponse(String message, String details) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.details = details;
	}
	
	public CommonResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "timestamp=" + timestamp + ", message=" + message + ", details=" + details;
	}
	
}
