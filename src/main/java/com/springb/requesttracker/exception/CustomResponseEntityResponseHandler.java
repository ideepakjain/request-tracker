package com.springb.requesttracker.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class CustomResponseEntityResponseHandler.
 */
@RestController
@ControllerAdvice
public class CustomResponseEntityResponseHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle all exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionResponse res = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle all exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleAllException(UserNotFoundException ex, WebRequest request) {
		ExceptionResponse res = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(res, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle all exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleAllException(RequestNotFoundException ex, WebRequest request) {
		ExceptionResponse res = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(res, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle all exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler
	protected ResponseEntity<Object> handleAllException(UserAlreadyExistsException ex, WebRequest request) {
		ExceptionResponse res = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
	}

}
