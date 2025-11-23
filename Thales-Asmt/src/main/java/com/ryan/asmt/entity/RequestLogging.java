package com.ryan.asmt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "REQ_LOGGING")
public class RequestLogging {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME")
	private Date time;
	
	@Column(name = "METHOD")
	private String method;
	
	@Column(name = "API")
	private String api;
	
	@Column(name = "HEADERS")
	private String headers;
	
	@Column(name = "PARAMS")
	private String params;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}

	public String getHeaders() {
		return headers;
	}
	public void setHeaders(String headers) {
		this.headers = headers;
	}
	
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	
}
