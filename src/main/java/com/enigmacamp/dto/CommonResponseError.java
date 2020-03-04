package com.enigmacamp.dto;

import io.swagger.annotations.ApiModelProperty;

public class CommonResponseError {

	@ApiModelProperty(value = "Response status code.", position = 0)
	private String status;
	
	@ApiModelProperty(value = "Response message", position = 1)
	private String message;

	public CommonResponseError() {
		super();
	}

	public CommonResponseError(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
