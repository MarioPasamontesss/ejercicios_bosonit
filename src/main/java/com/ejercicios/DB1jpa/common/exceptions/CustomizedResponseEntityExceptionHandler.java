package com.ejercicios.DB1jpa.common.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
//@ControllerAdvice
//@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

		@ExceptionHandler(NotFExceptions.class)
	public final ResponseEntity<CustomError> handleNotFoundException(NotFExceptions ex, WebRequest request) {
		CustomError customError = new CustomError(new Date(), ex.getMessage(),
				request.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		return new ResponseEntity<CustomError>(customError, HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(UnprocesableException.class)
	public final ResponseEntity<CustomError> handleNotValidationException(UnprocesableException ex, WebRequest request) {
		CustomError customError = new CustomError(new Date(), ex.getMessage(),
				request.getDescription(false),HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		return new ResponseEntity<CustomError>(customError, HttpStatus.NOT_ACCEPTABLE);
	}
}