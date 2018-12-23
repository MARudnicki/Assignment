package com.tg.sg.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tg.sg.api.model.ErrorResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	
	@RequestMapping(produces = "application/json")
    @ExceptionHandler(UnAuthrizeException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthorizationException(UnAuthrizeException ex) {
		ErrorResponse response=new ErrorResponse();
		response.setResponseCode("401");
		response.setResponseMessage(ex.getMessage());
		return response;
    }
	
	@RequestMapping(produces = "application/json")
    @ExceptionHandler(AccessForbiddenException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse accessForbidden(AccessForbiddenException ex) {
		ErrorResponse response=new ErrorResponse();
		response.setResponseCode("403");
		response.setResponseMessage(ex.getMessage());
		return response;
    }
}
