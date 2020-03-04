package com.enigmacamp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.enigmacamp.dto.CommonResponseError;

import javassist.NotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CommonResponseError> handleNotFound(Exception e, WebRequest req){
		CommonResponseError res = new CommonResponseError();
		res.setStatus("404");
		res.setMessage(e.getMessage());
		
		return new ResponseEntity<CommonResponseError>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<CommonResponseError> handleInternalServerError(Exception e, WebRequest req){
		CommonResponseError res = new CommonResponseError();
		res.setStatus("500");
		res.setMessage(e.getMessage());
		
		return new ResponseEntity<CommonResponseError>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<CommonResponseError> handleBadRequest(Exception e, WebRequest req){
		CommonResponseError res = new CommonResponseError();
		res.setStatus("400");
		res.setMessage(e.getMessage());
		
		return new ResponseEntity<CommonResponseError>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Unauthorized.class)
	public ResponseEntity<CommonResponseError> handleUnauthorized(Exception e, WebRequest req){
		CommonResponseError res = new CommonResponseError();
		res.setStatus("401");
		res.setMessage(e.getMessage());
		
		return new ResponseEntity<CommonResponseError>(res, HttpStatus.UNAUTHORIZED);
	}
}
