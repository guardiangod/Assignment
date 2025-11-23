package com.zendesk.enums;

public enum ExitCode {
	INVALID_ARGUMENT(128),
	PROCESS_ERROR(1);
	
	private int code;
	
	ExitCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
