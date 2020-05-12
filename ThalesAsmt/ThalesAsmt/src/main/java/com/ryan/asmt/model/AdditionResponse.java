package com.ryan.asmt.model;

public class AdditionResponse extends CommonResponse {

	private static final long serialVersionUID = 1L;
	
	private Integer result;
	
	public Integer getResult() {
		return result;
	}
	
	public void setResult(Integer result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return (super.toString() + ", result=" + result);
	}
	
}
