package com.tg.sg.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthrizeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnAuthrizeException() {
		super();
	}

	public UnAuthrizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnAuthrizeException(String message) {
		super(message);
	}

	public UnAuthrizeException(Throwable cause) {
		super(cause);
	}
}