package com.springb.requesttracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class RequestNotFoundException.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException {

	/**
	 * Instantiates a new request not found exception.
	 *
	 * @param message the message
	 */
	public RequestNotFoundException(String message) {
		super(message);
	}

}
