package com.enigmacamp.dto;

import java.util.Optional;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Class for standard response body")
public class CommonResponse<T> {

	@ApiModelProperty(value = "Response status code.", position = 0)
	private String status = "200";
	
	@ApiModelProperty(value = "Response message", position = 1)
	private String message = "Ok";
	
	@ApiModelProperty(value = "Response data", position = 2)
	private T data;

	public CommonResponse() {
	}
	
	public CommonResponse(T data) {
		this.data = data;
	}

	public CommonResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public CommonResponse(String status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
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

	public Optional<T> getData() {
		return Optional.ofNullable(data);
	}

	public void setData(T data) {
		this.data = data;
	}
}
