package com.recetario.backend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recetario.backend.dtos.ErrorDto;
import com.recetario.backend.exceptions.AppException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = { AppException.class })
	@ResponseBody
	public ResponseEntity<ErrorDto> handleException(AppException ex) {
		return ResponseEntity.status(ex.getCode()).body(ErrorDto.builder().message(ex.getMessage()).build());
	}
}
