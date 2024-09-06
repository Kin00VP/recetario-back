package com.recetario.backend.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus code;

	public AppException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

}
