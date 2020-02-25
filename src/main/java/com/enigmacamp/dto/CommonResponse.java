package com.enigmacamp.dto;

public class CommonResponse<T> {

	private String status = "200";
	private String message = "Ok";
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
